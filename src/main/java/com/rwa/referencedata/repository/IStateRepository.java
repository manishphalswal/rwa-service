package com.rwa.referencedata.repository;

import com.rwa.referencedata.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {
}
