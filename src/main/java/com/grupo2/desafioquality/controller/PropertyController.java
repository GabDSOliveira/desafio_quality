package com.grupo2.desafioquality.controller;

import com.grupo2.desafioquality.dto.PropertyAreaDto;
import com.grupo2.desafioquality.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping("/{id}/area")
    public PropertyAreaDto getPropertyArea(@PathVariable UUID id) {
        return propertyService.getPropertyArea(id);
    }
}
