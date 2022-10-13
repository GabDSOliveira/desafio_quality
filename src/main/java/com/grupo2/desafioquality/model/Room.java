package com.grupo2.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private String name;
    private double length;
    private double width;
}
