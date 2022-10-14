package com.grupo2.desafioquality.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.desafioquality.dto.*;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.service.DistrictService;
import com.grupo2.desafioquality.service.PropertyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PropertyControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private DistrictService districtService;

    private District district;

    @BeforeAll
    void setup() {
        district = saveDistrict();
    }

    @Test
    void getPropertyArea_returnPropertyDto_whenPropertyExists() throws Exception {
        // Given
        Property property = saveProperty(district);

        // When
        ResultActions result = mockMvc.perform(get("/property/{id}", property.getId().toString())
                .contentType(MediaType.APPLICATION_JSON));
        PropertyDto propertyDto = objectMapper.readValue(
                result.andReturn().getResponse().getContentAsString(),
                PropertyDto.class
        );
        List<RoomDto> expectedRoomDtos = property.getRooms().stream()
                .map(room -> RoomDto.fromRoom(room, room.getLength() * room.getWidth()))
                .collect(Collectors.toList());

        // Then
        result.andExpect(status().is2xxSuccessful());
        assertThat(propertyDto).isNotNull();
        assertThat(propertyDto.getId()).isEqualTo(property.getId());
        assertThat(propertyDto.getDistrictName()).isEqualTo(district.getName());
        assertThat(propertyDto.getArea()).isEqualTo(325);
        assertThat(propertyDto.getLargestRoom()).isEqualTo("LaundryRoom");
        assertThat(propertyDto.getRooms()).containsAll(expectedRoomDtos);
    }

    @Test
    void getPropertyArea_throwsNotFoundException_whenPropertyDoesNotExists() throws Exception {
        // When
        mockMvc.perform(get("/property/{id}", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createProperty_returnProperty_whenCreatePropertyDtoIsValidAndDistrictExists() throws Exception {
        // given
        CreatePropertyDto createPropertyDto = new CreatePropertyDto(
                "Apartamento",
                district.getId(),
                generateCreateRoomDtos()
        );

        // when
        ResultActions result = mockMvc.perform(post("/property")
                .content(objectMapper.writeValueAsString(createPropertyDto))
                .contentType(MediaType.APPLICATION_JSON));
        Property property = objectMapper.readValue(
                result.andReturn().getResponse().getContentAsString(),
                Property.class
        );

        // then
        result.andExpect(status().isCreated());
        assertThat(property.getId()).isNotNull();
        assertThat(property.getDistrict().getId()).isEqualTo(createPropertyDto.getDistrictId());
    }

    @Test
    void createProperty_throwNotFoundException_whenCreatePropertyDtoIsValidAndDistrictNotExists() throws Exception {
        // given
        CreatePropertyDto createPropertyDto = new CreatePropertyDto(
                "Apartamento",
                UUID.randomUUID(),
                generateCreateRoomDtos()
        );

        // when / then
        mockMvc.perform(post("/property")
                .content(objectMapper.writeValueAsString(createPropertyDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createProperty_throwBadRequestException_whenCreatePropertyDtoIsNotValid() throws Exception {
        // given
        CreatePropertyDto createPropertyDto = new CreatePropertyDto(
                "Apartamento",
                null,
                generateCreateRoomDtos()
        );

        // when / then
        mockMvc.perform(post("/property")
                        .content(objectMapper.writeValueAsString(createPropertyDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private District saveDistrict() {
        CreateDistrictDto createDistrictDto = new CreateDistrictDto("Marco", BigDecimal.valueOf(1000));
        return districtService.createDistrict(createDistrictDto);
    }

    private Property saveProperty(District district) {
        CreatePropertyDto createPropertyDto = new CreatePropertyDto(
                "Apartamento",
                district.getId(),
                generateCreateRoomDtos()
        );

        return propertyService.createProperty(createPropertyDto);
    }

    private List<CreateRoomDto> generateCreateRoomDtos() {
        return Arrays.asList(
                new CreateRoomDto("Bathroom", 10, 10),
                new CreateRoomDto("LaundryRoom", 15, 15)
        );
    }
}
