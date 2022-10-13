package com.grupo2.desafioquality.controller;

import com.grupo2.desafioquality.dto.CreateDistrictDto;
import com.grupo2.desafioquality.model.Property;
import com.grupo2.desafioquality.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("district")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService districtService;

    @PostMapping
    public Property createDistrict(@RequestBody @Valid CreateDistrictDto createDistrictDto) {
        return null;
    }
}
