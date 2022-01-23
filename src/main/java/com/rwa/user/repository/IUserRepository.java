package com.rwa.user.repository;

import com.rwa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    int deleteByUsername(String username);

    @Modifying
    @Query("update User u set u.role = :role where u.id = :id")
    int updateRole(@Param("id") Long id, @Param("role") String role);

    @Modifying
    @Query("update User u set u.userPassword = :newPassword where u.username = :username")
    int changePassword(@Param("username") String username, @Param("newPassword") String newPassword);
}
