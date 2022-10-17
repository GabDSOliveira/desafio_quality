package com.grupo2.desafioquality.service;

import com.grupo2.desafioquality.dto.CreateDistrictDto;
import com.grupo2.desafioquality.model.District;
import com.grupo2.desafioquality.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/** Classe de serviço do bairro.
 * @since Release 01 da aplicação.
 */
@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;

    /** Cria o bairro no repositório
     * @param CreateDistrictDto createDistrictDto - DTO do bairro
     * @return District - retorna o bairro criado
     */
    public District createDistrict(CreateDistrictDto createDistrictDto) {
        District district = new District(UUID.randomUUID(), createDistrictDto.getName(), createDistrictDto.getValue());
        districtRepository.saveDistrict(district);
        return district;
    }

    /** Encontra o bairro pelo ID passado no parâmetro
     * @param UUID districtId - Id do bairro
     * @return Optional<District> - retorna o optional do bairro
     */
    public Optional<District> findById(UUID districtId) {
        return this.districtRepository.findById(districtId);
    }
}
