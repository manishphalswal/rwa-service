package com.rwa.referencedata.service;

import com.rwa.referencedata.dao.ReferenceDAOWrapper;
import com.rwa.referencedata.domain.StateDto;
import com.rwa.referencedata.domain.VillageDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReferenceDataService {
    private final ReferenceDAOWrapper daoWrapper;

    public List<VillageDto> getVillages() {
        return daoWrapper.getVillages();
    }

    public List<VillageDto> getVillagesByState(final Long stateId) {
        return daoWrapper.getVillagesByState(stateId);
    }

    public VillageDto getVillageById(final Long id) {
        return daoWrapper.getVillageById(id);
    }

    public List<StateDto> getStates() {
        return daoWrapper.getStates();
    }

    public StateDto getStateById(final Long id) {
        return daoWrapper.getStateById(id);
    }
}
