package com.grupo2.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreatePropertyDto {
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome não exceder 30 caracteres.")
    @Pattern(regexp = "^[A-Z]\\S+$", message = "O nome da propriedade deve começar com a uma letra maiúscula.")
    private String name;

    @NotNull
    private UUID districtId;

    @NotEmpty
    @Valid
    private List<CreateRoomDto> rooms;
}
