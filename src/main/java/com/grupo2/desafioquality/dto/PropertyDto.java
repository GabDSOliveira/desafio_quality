package com.grupo2.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PropertyDto {
    private UUID id;
    private String districtName;
    private double area;
    private BigDecimal price;
    private String largestRoom;
    private List<RoomDto> rooms;
}
