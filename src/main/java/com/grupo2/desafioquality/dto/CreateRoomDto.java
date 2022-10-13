package com.grupo2.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class CreateRoomDto {
    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres")
    @Pattern(regexp = "^[A-Z]\\S+$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    private String name;

    @NotBlank(message = "O comprimento do cômodo não pode estar vazio.")
    @Positive
    @Max(value = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private double length;

    @NotBlank(message = "A largura do cômodo não pode estar vazia.")
    @Positive
    @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double width;
}
