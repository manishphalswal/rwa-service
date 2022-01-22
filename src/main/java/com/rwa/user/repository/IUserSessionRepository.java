package com.rwa.user.repository;

import com.rwa.user.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserSessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByUsername(String username);

    @Modifying
    @Query("update UserSession u set u.role = :role where u.id = :id")
    int updateRole(@Param("id") Long id, @Param("role") String role);
}
