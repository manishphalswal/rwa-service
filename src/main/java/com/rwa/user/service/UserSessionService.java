package com.rwa.user.service;

import com.rwa.user.dao.UserSessionDAOWrapper;
import com.rwa.user.domain.UserSessionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserSessionService {
    private final UserSessionDAOWrapper daoWrapper;

    public UserSessionDTO getUserSessionByUsername(final String username) {
        return daoWrapper.getUserSession(username);
    }

    public UserSessionDTO saveUserSession(final UserSessionDTO userSessionDTO) {
        return daoWrapper.saveUserSession(userSessionDTO);
    }

    public UserSessionDTO updateUserSession(final UserSessionDTO userSessionDTO) {
        return daoWrapper.updateUserSession(userSessionDTO);
    }

    public void updateLoginStatus(final String username) {
        daoWrapper.updateLoginStatus(username);
    }

    public void updateLogoutStatus(final String username) {
        daoWrapper.updateLogoutStatus(username);
    }

    public boolean isLoggedIn(final String username) {
        return daoWrapper.isLoggedIn(username);
    }
}
