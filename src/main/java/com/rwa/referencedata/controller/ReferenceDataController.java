package com.rwa.referencedata.controller;

import com.rwa.referencedata.domain.StateDTO;
import com.rwa.referencedata.domain.VillageDTO;
import com.rwa.referencedata.service.ReferenceDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reference")
public class ReferenceDataController {
    private ReferenceDataService service;

    @GetMapping("/villages")
    public ResponseEntity<List<VillageDTO>> getVillages() {
        return ResponseEntity.ok(service.getVillages());
    }

    @GetMapping("/villages/state/{stateId}")
    public ResponseEntity<List<VillageDTO>> getVillages(@PathVariable("stateId") Long stateId) {
        return ResponseEntity.ok(service.getVillagesByState(stateId));
    }

    @GetMapping("/villages/{id}")
    public ResponseEntity<VillageDTO> getVillage(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getVillageById(id));
    }

    @GetMapping("/states")
    public ResponseEntity<List<StateDTO>> getStates() {
        return ResponseEntity.ok(service.getStates());
    }

    @GetMapping("/states/{id}")
    public ResponseEntity<StateDTO> getState(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getStateById(id));
    }
}
