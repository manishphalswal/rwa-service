package com.rwa.referencedata.repository;

import com.rwa.referencedata.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVillageRepository extends JpaRepository<Village, Long> {

    List<Village> findAllByStateId(Long stateId);
}
