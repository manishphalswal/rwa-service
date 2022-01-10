package com.rwa.service.user;

import com.rwa.dao.user.UserDAOWrapper;
import com.rwa.domain.user.UserDto;
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
