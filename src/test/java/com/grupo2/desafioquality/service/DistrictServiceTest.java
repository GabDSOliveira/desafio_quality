package com.grupo2.desafioquality.service;

import com.grupo2.desafioquality.dto.CreateDistrictDto;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.repository.DistrictRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    DistrictService districtService;

    @Test
    void createDistrict_returnNewDistrict_whenValidData() {
        CreateDistrictDto createDistrictDto = setupCreateDistrictDto();
        UUID id = UUID.randomUUID();
        District district = new District(id, "District", BigDecimal.valueOf(500));

        District result = districtService.createDistrict(createDistrictDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(district.getName());
        assertThat(result.getSquareMeterValue()).isEqualTo(district.getSquareMeterValue());
        Mockito.verify(districtRepository, Mockito.times(1)).saveDistrict(result);
    }

    @Test
    void findById_returnDistrict_whenValidId() {
        UUID id = UUID.randomUUID();
        District district = new District(id, "District", BigDecimal.valueOf(500));

        Mockito.when(districtRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(district));

        Optional<District> result = districtService.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.get().getName()).isEqualTo(district.getName());
        assertThat(result.get().getSquareMeterValue()).isEqualTo(district.getSquareMeterValue());
    }

    private CreateDistrictDto setupCreateDistrictDto() {
        return new CreateDistrictDto("District", BigDecimal.valueOf(500));
    }
}
