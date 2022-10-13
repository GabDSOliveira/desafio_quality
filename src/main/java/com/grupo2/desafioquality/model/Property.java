package com.grupo2.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Property {
    private UUID id;
    private String name;
    private District district;
    private List<Room> rooms;
}
