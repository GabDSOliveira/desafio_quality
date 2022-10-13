package com.grupo2.desafioquality.service;

import com.grupo2.desafioquality.dto.PropertyAreaDto;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.model.Room;
import com.grupo2.desafioquality.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyAreaDto getPropertyArea(UUID propertyId) {
        Property property = propertyRepository.findPropertyById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        float area = property.getRooms()
                .stream()
                .map(this::calculateRoomArea)
                .reduce(0F, Float::sum);

        return new PropertyAreaDto(propertyId, area);
    }

    private float calculateRoomArea(Room room) {
        return room.getLength() * room.getWidth();
    }
}
