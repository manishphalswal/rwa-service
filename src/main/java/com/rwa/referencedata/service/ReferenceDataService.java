package com.rwa.referencedata.service;

import com.rwa.referencedata.dao.ReferenceDAOWrapper;
import com.rwa.referencedata.domain.StateDTO;
import com.rwa.referencedata.domain.VillageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReferenceDataService {
    private final ReferenceDAOWrapper daoWrapper;

    public List<VillageDTO> getVillages() {
        return daoWrapper.getVillages();
    }

    public List<VillageDTO> getVillagesByState(final Long stateId) {
        return daoWrapper.getVillagesByState(stateId);
    }

    public VillageDTO getVillageById(final Long id) {
        return daoWrapper.getVillageById(id);
    }

    public List<StateDTO> getStates() {
        return daoWrapper.getStates();
    }

    public StateDTO getStateById(final Long id) {
        return daoWrapper.getStateById(id);
    }
}
