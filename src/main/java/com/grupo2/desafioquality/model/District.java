package com.grupo2.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class District {
    private UUID id;
    private String name;
    private BigDecimal squareMeterValue;
}
