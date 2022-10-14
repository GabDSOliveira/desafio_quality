package com.grupo2.desafioquality.service;

import com.grupo2.desafioquality.dto.CreateDistrictDto;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;

    public District createDistrict(CreateDistrictDto createDistrictDto) {
        District district = new District(UUID.randomUUID(), createDistrictDto.getName(), createDistrictDto.getValue());
        districtRepository.saveDistrict(district);
        return district;
    }

    public Optional<District> findById(UUID districtId) {
        return this.districtRepository.findById(districtId);
    }
}
