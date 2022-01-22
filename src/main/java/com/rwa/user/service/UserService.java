package com.rwa.user.service;

import com.rwa.common.domain.Role;
import com.rwa.user.dao.UserDAOWrapper;
import com.rwa.user.domain.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserDAOWrapper userDAOWrapper;

    private final UserSessionService userSessionService;

    public List<UserDTO> getUsers() {
        return this.userDAOWrapper.getUsers();
    }

    public UserDTO getUserById(final Long id) {
        return this.userDAOWrapper.getUserById(id);
    }

    public UserDTO getUserByUsername(final String username) {
        return this.userDAOWrapper.getUserByUsername(username);
    }

    public UserDTO saveUser(final UserDTO userDto) {
        return this.userDAOWrapper.saveUser(userDto);
    }

    public UserDTO updateUser(final UserDTO userDto) {
        return this.userDAOWrapper.updateUser(userDto);
    }

    public void deleteUserById(final Long id) {
        this.userDAOWrapper.deleteUserById(id);
    }

    public void updateUserRole(final Long id, final Role role) {
        userDAOWrapper.updateUserRole(id, role);
        this.userSessionService.updateUserRole(id, role);
    }

}
