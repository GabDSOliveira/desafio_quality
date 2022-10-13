package com.grupo2.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateDistrictDto {
    @NotBlank
    @Size(max = 45)
    private String name;

    @NotNull
    @Digits(integer = 13, fraction = 2)
    private BigDecimal value;
}
