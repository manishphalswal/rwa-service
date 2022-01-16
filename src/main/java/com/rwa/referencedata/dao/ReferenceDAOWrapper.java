package com.rwa.referencedata.dao;

import com.rwa.common.util.RWAModelMapper;
import com.rwa.referencedata.domain.StateDto;
import com.rwa.referencedata.domain.VillageDto;
import com.rwa.referencedata.exception.ResourceNotFoundException;
import com.rwa.referencedata.repository.IStateRepository;
import com.rwa.referencedata.repository.IVillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReferenceDAOWrapper {

    private final RWAModelMapper rwaModelMapper;

    private final IVillageRepository villageRepository;

    private final IStateRepository stateRepository;

    public List<VillageDto> getVillages() {
        return villageRepository.findAll()
                .parallelStream()
                .map(rwaModelMapper::mapVillageEntityToBean)
                .collect(Collectors.toList());
    }

    public List<VillageDto> getVillagesByState(final Long stateId) {
        return villageRepository.findAllByStateId(stateId)
                .stream().map(rwaModelMapper::mapVillageEntityToBean)
                .collect(Collectors.toList());
    }

    public VillageDto getVillageById(final Long id) {
        return rwaModelMapper.mapVillageEntityToBean(villageRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }


    public List<StateDto> getStates() {
        return stateRepository.findAll()
                .parallelStream()
                .map(rwaModelMapper::mapStateEntityToBean)
                .collect(Collectors.toList());
    }

    public StateDto getStateById(final Long id) {
        return rwaModelMapper.mapStateEntityToBean(stateRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }
}
