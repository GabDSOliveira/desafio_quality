package com.grupo2.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class CreateRoomDto {
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z]\\S+$", message = "Deve começar com letra maiúscula")
    private String name;

    @Positive
    @Max(33)
    private double length;

    @Positive
    @Max(25)
    private double width;
}
