package com.grupo2.desafioquality.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.grupo2.desafioquality.dto.CreatePropertyDto;
import com.grupo2.desafioquality.dto.CreateRoomDto;
import com.grupo2.desafioquality.dto.PropertyDto;
import com.grupo2.desafioquality.dto.RoomDto;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.model.Room;
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
        PropertyDto propertyDto = setupPropertyDto();
        Property property = setupProperty();

        Mockito.when(propertyRepository.findPropertyById(ArgumentMatchers.any())).thenReturn(Optional.of(property));
        PropertyDto result = propertyService.getPropertyArea(propertyDto.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(propertyDto.getId());
        assertThat(result.getDistrictName()).isEqualTo(propertyDto.getDistrictName());
        assertThat(result.getArea()).isEqualTo(propertyDto.getArea());
        assertThat(result.getLargestRoom()).isEqualTo(propertyDto.getLargestRoom());
        assertThat(result.getRooms()).hasSameSizeAs(propertyDto.getRooms());
    }

    @Test
    void getPropertyArea_throwsError_whenPropertyNotFound() {
        PropertyDto propertyDto = setupPropertyDto();

        assertThrows(RuntimeException.class, () -> propertyService.getPropertyArea(propertyDto.getId()));
    }

    private CreatePropertyDto setupCreatePropertyDto() {
        String name = "Casa";
        UUID districtId = UUID.randomUUID();
        List<CreateRoomDto> rooms = new ArrayList<>();
        CreateRoomDto room = new CreateRoomDto("Quarto", 10, 10);
        rooms.add(room);

        return new CreatePropertyDto(name, districtId, rooms);
    }

    private PropertyDto setupPropertyDto() {
        UUID id = UUID.randomUUID();
        String name = "District";
        double area = 200.0;
        BigDecimal price = BigDecimal.valueOf(100000);
        String largestRoom = "Quarto";
        List<RoomDto> rooms = new ArrayList<>();
        RoomDto room = new RoomDto("Quarto", 200.0);
        rooms.add(room);

        return new PropertyDto(id, name, area, price, largestRoom, rooms);
    }
    private Property setupProperty() {
        UUID id = UUID.randomUUID();
        String name = "District";
            District district = new District(UUID.randomUUID(), "District", BigDecimal.valueOf(500));
        List<Room> rooms = new ArrayList<>();
        Room room = new Room("Quarto", 20, 10);
        rooms.add(room);

        return new Property(id, name, district, rooms);
    }
}