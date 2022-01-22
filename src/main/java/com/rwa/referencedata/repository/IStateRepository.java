package com.rwa.referencedata.repository;

import com.rwa.referencedata.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStateRepository extends JpaRepository<State, Long> {
}
