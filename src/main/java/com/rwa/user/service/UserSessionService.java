package com.rwa.user.service;

import com.rwa.common.domain.Role;
import com.rwa.user.dao.UserSessionDAOWrapper;
import com.rwa.user.domain.UserSessionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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

    public void updateUserRole(final Long id, final Role role) {
        daoWrapper.updateUserRole(id, role);
    }

}
