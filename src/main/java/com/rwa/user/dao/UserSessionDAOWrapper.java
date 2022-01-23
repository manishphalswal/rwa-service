package com.rwa.user.dao;

import com.rwa.common.util.RWAModelMapper;
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

    private final RWAModelMapper rwaModelMapper;

    public UserSessionDTO getUserSession(final String username) {
        return Arrays.stream(repository.findByUsername(username))
                .findFirst()
                .map(object -> rwaModelMapper.mapObjectArrayToUserSessionDTOBean((Object[]) object))
                .orElseThrow(() -> new UsernameNotFoundException("Username:::" + username));
    }

    public UserSessionDTO saveUserSession(final UserSessionDTO userSessionDTO) {
        return rwaModelMapper.mapUserSessionEntityToBean(repository.save(rwaModelMapper.mapUserSessionBeanToEntity(userSessionDTO)));
    }

    public UserSessionDTO updateUserSession(final UserSessionDTO userSessionDTO) {
        return rwaModelMapper.mapUserSessionEntityToBean(repository.save(rwaModelMapper.mapUserSessionBeanToEntity(userSessionDTO)));
    }

    public void updateLoginStatus(final String username) {
        UserSessionDTO userSessionDTOFromDB = getUserSession(username);
        UserSession userSessionFromDB = rwaModelMapper.mapUserSessionBeanToEntity(userSessionDTOFromDB);
        userSessionFromDB.setLoggedIn(true);
        userSessionFromDB.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
        userSessionFromDB.setLastLogin(userSessionFromDB.getLoginTime());
        repository.save(userSessionFromDB);
    }

    public void updateLogoutStatus(final String username) {
        repository.updateLogoutStatus(username);
    }

    public boolean isLoggedIn(final String username) {
        return getUserSession(username).isLoggedIn();
    }
}
