package com.rwa.referencedata.controller;

import com.rwa.referencedata.domain.StateDto;
import com.rwa.referencedata.domain.VillageDto;
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
    public ResponseEntity<List<VillageDto>> getVillages() {
        return ResponseEntity.ok(service.getVillages());
    }

    @GetMapping("/villages/state/{stateId}")
    public ResponseEntity<List<VillageDto>> getVillages(@PathVariable("stateId") Long stateId) {
        return ResponseEntity.ok(service.getVillagesByState(stateId));
    }

    @GetMapping("/villages/{id}")
    public ResponseEntity<VillageDto> getVillage(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getVillageById(id));
    }

    @GetMapping("/states")
    public ResponseEntity<List<StateDto>> getStates() {
        return ResponseEntity.ok(service.getStates());
    }

    @GetMapping("/states/{id}")
    public ResponseEntity<StateDto> getState(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getStateById(id));
    }
}
