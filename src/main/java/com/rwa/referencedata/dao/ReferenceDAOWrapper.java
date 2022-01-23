package com.rwa.referencedata.dao;

import com.rwa.common.util.mapper.UserModelMapper;
import com.rwa.referencedata.domain.StateDTO;
import com.rwa.referencedata.domain.VillageDTO;
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

    private final UserModelMapper userModelMapper;

    private final IVillageRepository villageRepository;

    private final IStateRepository stateRepository;

    public List<VillageDTO> getVillages() {
        return villageRepository.findAll()
                .parallelStream()
                .map(userModelMapper::mapVillageEntityToBean)
                .collect(Collectors.toList());
    }

    public List<VillageDTO> getVillagesByState(final Long stateId) {
        return villageRepository.findAllByStateId(stateId)
                .stream().map(userModelMapper::mapVillageEntityToBean)
                .collect(Collectors.toList());
    }

    public VillageDTO getVillageById(final Long id) {
        return userModelMapper.mapVillageEntityToBean(villageRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }


    public List<StateDTO> getStates() {
        return stateRepository.findAll()
                .parallelStream()
                .map(userModelMapper::mapStateEntityToBean)
                .collect(Collectors.toList());
    }

    public StateDTO getStateById(final Long id) {
        return userModelMapper.mapStateEntityToBean(stateRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }
}
