package com.rwa.user.service;

import com.rwa.common.domain.Role;
import com.rwa.security.domain.ChangePassword;
import com.rwa.security.domain.ResetPassword;
import com.rwa.user.dao.UserDAOWrapper;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.exception.UsernameAssignedException;
import com.rwa.user.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserDAOWrapper userDAOWrapper;

    public List<UserDTO> getUsers() {
        return this.userDAOWrapper.getUsers();
    }

    public UserDTO getUserById(final Long id) {
        return this.userDAOWrapper.getUserById(id);
    }

    public UserDTO getUserByUsername(final String username) {
        return this.userDAOWrapper.getUserByUsername(username);
    }

    public UserDTO saveUser(final UserDTO userDto, final String createdBy) {
        try {
            return this.userDAOWrapper.saveUser(userDto, createdBy);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("unq_t_user")) {
                log.error("User already exists with username:" + userDto.getMobileNo());
                throw new UsernameAssignedException();
            }
            throw ex;
        }
    }

    public UserDTO updateUser(final UserDTO userDto) {
        return this.userDAOWrapper.updateUser(userDto);
    }

    public void deleteUserById(final Long id) {
        this.userDAOWrapper.deleteUserById(id);
    }

    public void updateUserRole(final Long id, final Role role) {
        userDAOWrapper.updateUserRole(id, role);
    }

    public boolean changePassword(final ChangePassword changePassword) {
        UserDTO userDTO = userDAOWrapper.getUserByUsername(changePassword.getUsername());

        if (UserUtil.matchPassword(changePassword.getOldPassword(), userDTO.getUserPassword())) {
            return userDAOWrapper.updatePassword(changePassword.getUsername(), changePassword.getNewPassword());
        } else {
            throw new BadCredentialsException("Old password does not match");
        }
    }

    public boolean resetPassword(final ResetPassword resetPassword) {
        return userDAOWrapper.updatePassword(resetPassword.getUsername(), resetPassword.getNewPassword());
    }

}
