package com.grupo2.desafioquality.service;

import com.grupo2.desafioquality.dto.CreatePropertyDto;
import com.grupo2.desafioquality.dto.CreateRoomDto;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {
    @Mock
    PropertyRepository propertyRepository;

    @Mock
    DistrictService districtService;

    @InjectMocks
    PropertyService propertyService;

    @Test
    void createProperty_returnNewProperty_whenValidData() {
        CreatePropertyDto createPropertyDto = setupCreatePropertyDto();

        District district = new District(createPropertyDto.getDistrictId(), "Zona Sul", BigDecimal.valueOf(500));

        Mockito.when(districtService.findById(ArgumentMatchers.any())).thenReturn(Optional.of(district));

        Property result = propertyService.createProperty(createPropertyDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(createPropertyDto.getName());
        assertThat(result.getDistrict()).isEqualTo(district);
        assertThat(result.getRooms().size()).isEqualTo(createPropertyDto.getRooms().size());
        Mockito.verify(propertyRepository, Mockito.times(1)).saveProperty(result);
    }

    @Test
    void createProperty_throwsError_whenDistrictNotFound() {
        CreatePropertyDto createPropertyDto = setupCreatePropertyDto();

        assertThrows(RuntimeException.class, () -> propertyService.createProperty(createPropertyDto));
    }

    @Test
    void getPropertyArea_returnPropertyInfo_whenValidPropertyId() {
    }

    @Test
    void getPropertyArea_throwsError_whenPropertyNotFound() {
    }

    private CreatePropertyDto setupCreatePropertyDto() {
        String name = "Casa";
        UUID districtId = UUID.randomUUID();
        List<CreateRoomDto> rooms = new ArrayList<>();
        CreateRoomDto room = new CreateRoomDto("Quarto", 10, 10);
        rooms.add(room);

        return new CreatePropertyDto(name, districtId, rooms);
    }
}