package com.rwa.user.repository;

import com.rwa.user.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserSessionRepository extends JpaRepository<UserSession, Long> {

    @Query("select us.id, us.username, u.userPassword, u.role, us.loggedIn, us.active, us.locked, us.credExpired, us.createdDate, us.loginTime, us.lastLogin " +
            "from com.rwa.user.entity.UserSession us inner join com.rwa.user.entity.User u on us.username=u.username where us.username= :username")
    Object[] findByUsername(@Param("username") String username);

    @Modifying
    @Query("update UserSession us set us.token = null where us.username = :username")
    int updateLogoutDetails(@Param("username") String username);
}
