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
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z]\\S+$", message = "Deve começar com letra maiúscula")
    private String name;

    @NotNull
    private UUID districtId;

    @NotEmpty
    @Valid
    private List<CreateRoomDto> rooms;
}
