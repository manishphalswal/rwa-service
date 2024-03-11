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

    public void updateLoginDetails(final String username, final String token) {
        daoWrapper.updateLoginDetails(username, token);
    }

    public void updateLogoutDetails(final String username) {
        daoWrapper.updateLogoutDetails(username);
    }

    public boolean validateTokenFromDB(final String username, final String token) {
        return daoWrapper.validateTokenFromDB(username, token);
    }
}
