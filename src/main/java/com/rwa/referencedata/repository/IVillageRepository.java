package com.rwa.referencedata.repository;

import com.rwa.referencedata.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVillageRepository extends JpaRepository<Village, Long> {

    List<Village> findAllByStateId(Long stateId);
}
