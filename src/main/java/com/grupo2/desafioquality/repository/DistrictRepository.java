package com.grupo2.desafioquality.repository;

import com.grupo2.desafioquality.model.District;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DistrictRepository {
    private final List<District> districts = new ArrayList<>();

    public DistrictRepository() {
        // TODO: apagar ????
        District district = new District(
                UUID.fromString("2db0d76c-9e95-4d91-a120-09993f4c2bfb"),
                "Parque Verde",
                BigDecimal.valueOf(2000)
        );
        this.districts.add(district);
    }

    public void saveDistrict(District district) {
        this.districts.add(district);
    }

    public Optional<District> findById(UUID districtId) {
        return this.districts.stream().filter(district -> district.getId().equals(districtId)).findFirst();
    }
}
