package com.grupo2.desafioquality.service;

import com.grupo2.desafioquality.dto.PropertyDto;
import com.grupo2.desafioquality.dto.RoomDto;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.model.Room;
import com.grupo2.desafioquality.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyDto getPropertyArea(UUID propertyId) {
        Property property = propertyRepository.findPropertyById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        District district = property.getDistrict();

        double area = calculatePropertyArea(property);

        BigDecimal price = calculatePropertyPrice(property, area);

        List<Room> rooms = property.getRooms();
        String largestRoom = calculateLargestRoom(rooms).getName();

        List<RoomDto> roomDtos = rooms.stream()
                .map(room -> RoomDto.fromRoom(room, area))
                .collect(Collectors.toList());

        return new PropertyDto(
                propertyId,
                district.getName(),
                area,
                price,
                largestRoom,
                roomDtos
        );
    }

    private Room calculateLargestRoom(List<Room> rooms) {
        return rooms.stream()
                .max(Comparator.comparingDouble(this::calculateRoomArea)).orElseThrow();
    }

    private double calculatePropertyArea(Property property) {
        return property.getRooms()
                .stream()
                .map(this::calculateRoomArea)
                .reduce(0D, Double::sum);
    }

    private BigDecimal calculatePropertyPrice(Property property, double area) {
        return property.getDistrict().getSquareMeterValue().multiply(BigDecimal.valueOf(area));
    }

    private double calculateRoomArea(Room room) {
        return room.getLength() * room.getWidth();
    }
}
