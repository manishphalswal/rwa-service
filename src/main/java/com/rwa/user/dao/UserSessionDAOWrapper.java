package com.rwa.user.dao;

import com.rwa.common.util.mapper.UserModelMapper;
import com.rwa.user.domain.UserSessionDTO;
import com.rwa.user.entity.UserSession;
import com.rwa.user.repository.IUserSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserSessionDAOWrapper {
    private final IUserSessionRepository repository;

    private final UserModelMapper userModelMapper;

    public UserSessionDTO getUserSession(final String username) {
        return Arrays.stream(repository.findByUsername(username))
                .findFirst()
                .map(object -> userModelMapper.mapObjectArrayToUserSessionDTOBean((Object[]) object))
                .orElseThrow(() -> new UsernameNotFoundException("Username:::" + username));
    }

    public UserSessionDTO saveUserSession(final UserSessionDTO userSessionDTO) {
        return userModelMapper.mapUserSessionEntityToBean(repository.save(userModelMapper.mapUserSessionBeanToEntity(userSessionDTO)));
    }

    public UserSessionDTO updateUserSession(final UserSessionDTO userSessionDTO) {
        return userModelMapper.mapUserSessionEntityToBean(repository.save(userModelMapper.mapUserSessionBeanToEntity(userSessionDTO)));
    }

    public void updateLoginDetails(final String username, final String token) {
        UserSessionDTO userSessionDTOFromDB = getUserSession(username);
        UserSession userSessionFromDB = userModelMapper.mapUserSessionBeanToEntity(userSessionDTOFromDB);
        userSessionFromDB.setToken(token);
        userSessionFromDB.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
        userSessionFromDB.setLastLogin(userSessionFromDB.getLoginTime());
        repository.save(userSessionFromDB);
    }

    public void updateLogoutDetails(final String username) {
        repository.updateLogoutDetails(username);
    }

    public boolean validateTokenFromDB(final String username, final String token) {
        return token.equals(getUserSession(username).getToken());
    }
}
