package com.rwa.user.dao;

import com.rwa.common.domain.Role;
import com.rwa.common.util.RWAModelMapper;
import com.rwa.user.domain.UserSessionDTO;
import com.rwa.user.repository.IUserSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSessionDAOWrapper {
    private final IUserSessionRepository repository;

    private final RWAModelMapper rwaModelMapper;

    public UserSessionDTO getUserSession(final String username) {
        return rwaModelMapper.mapUserSessionEntityToBean(repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username:::" + username)));
    }

    public UserSessionDTO saveUserSession(final UserSessionDTO userSessionDTO) {
        return rwaModelMapper.mapUserSessionEntityToBean(rwaModelMapper.mapUserSessionBeanToEntity(userSessionDTO));
    }

    public UserSessionDTO updateUserSession(final UserSessionDTO userSessionDTO) {
        return rwaModelMapper.mapUserSessionEntityToBean(rwaModelMapper.mapUserSessionBeanToEntity(userSessionDTO));
    }

    public void updateUserRole(final Long id, final Role role) {
        repository.updateRole(id, role.name());
    }
}
