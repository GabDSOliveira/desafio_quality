package com.grupo2.desafioquality.controller;

import com.grupo2.desafioquality.dto.CreatePropertyDto;
import com.grupo2.desafioquality.dto.PropertyDto;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/** RestController que gerencia as rotas das propriedades */
@RestController
@RequestMapping("property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    /** Cria uma propriedade no repositório
     * @param CreatePropertyDto createPropertyDto - DTO de Property
     * @return Property - retorna a propriedade criada
     */
    @PostMapping
    public Property createProperty(@RequestBody @Valid CreatePropertyDto createPropertyDto) {
        return propertyService.createProperty(createPropertyDto);
    }

    /** Retorna a área da propriedade com o id passado no parâmetro
     * @param UUID id - ID da propriedade
     * @return PropertyDto - informações da propriedade
     */
    @GetMapping("/{id}")
    public PropertyDto getPropertyArea(@PathVariable UUID id) {
        return propertyService.getPropertyArea(id);
    }
}
