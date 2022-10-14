package com.grupo2.desafioquality.controller;

import com.grupo2.desafioquality.dto.CreatePropertyDto;
import com.grupo2.desafioquality.dto.PropertyDto;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Property createProperty(@RequestBody @Valid CreatePropertyDto createPropertyDto) {
        return propertyService.createProperty(createPropertyDto);
    }

    @GetMapping("/{id}")
    public PropertyDto getPropertyArea(@PathVariable UUID id) {
        return propertyService.getPropertyArea(id);
    }
}
