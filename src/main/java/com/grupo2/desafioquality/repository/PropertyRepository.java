package com.grupo2.desafioquality.repository;

import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.model.Room;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class PropertyRepository {
    private final List<Property> properties = new ArrayList<>();

    public PropertyRepository() {
        District district = new District(UUID.randomUUID(), "Parque Verde", BigDecimal.valueOf(1000));
        Room restRoom = new Room("RestRoom", 30f, 30f);
        Room laundryRoom = new Room("LandryRoom", 10f, 10f);
        Property property = new Property(
                UUID.fromString("e3a7c944-fd54-4836-acb9-b89637afe0f1"),
                "Apertamento",
                district,
                Arrays.asList(restRoom, laundryRoom)
        );

        properties.add(property);
    }

    public void saveProperty(Property property) {
        this.properties.add(property);
    }

    public Optional<Property> findPropertyById(UUID id) {
        return this.properties.stream()
                .filter(property -> property.getId().equals(id)).findFirst();
    }
}
