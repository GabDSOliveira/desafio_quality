package com.grupo2.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateDistrictDto {
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio.")
    @Digits(integer = 13, fraction = 2)
    private BigDecimal value;
}
