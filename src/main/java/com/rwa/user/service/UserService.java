package com.rwa.user.service;

import com.rwa.user.dao.UserDAOWrapper;
import com.rwa.user.domain.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserDAOWrapper userDAOWrapper;

    public List<UserDto> getUsers() {
        return this.userDAOWrapper.getUsers();
    }

    public UserDto getUserById(final Long id) {
        return this.userDAOWrapper.getUserById(id);
    }

    public UserDto getUserByUsername(final String username) {
        return this.userDAOWrapper.getUserByUsername(username);
    }

    public UserDto saveUser(final UserDto userDto) {
        return this.userDAOWrapper.saveUser(userDto);
    }

    public UserDto updateUser(final UserDto userDto) {
        return this.userDAOWrapper.updateUser(userDto);
    }

    public void deleteUserById(final Long id) {
        this.userDAOWrapper.deleteUserById(id);
    }

}
