package com.rwa.user.dao;

import com.rwa.common.domain.Role;
import com.rwa.common.util.mapper.UserModelMapper;
import com.rwa.exception.InvalidRequestException;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.entity.User;
import com.rwa.user.exception.IdUsernameMismatchException;
import com.rwa.user.exception.UserNotFoundException;
import com.rwa.user.exception.UsernameAssignedException;
import com.rwa.user.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserDAOWrapper {

    private final IUserRepository userRepository;
    
    private final UserModelMapper userModelMapper;

    public List<UserDTO> getUsers() {
        return this.userRepository.findAll()
                .parallelStream()
                .map(userModelMapper::mapUserEntityToBean)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(final Long id) {
        return userModelMapper.mapUserEntityToBean(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString())));
    }

    public UserDTO getUserByUsername(final String username) {
        return userModelMapper.mapUserEntityToBean(this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    public UserDTO saveUser(final UserDTO userDto, final String createdBy) throws UsernameAssignedException, DataIntegrityViolationException {
        User user = userModelMapper.mapUserBeanToEntity(userDto);
        user.setCreatedBy(createdBy);
        user.getAddress().setCreatedBy(createdBy);
        return userModelMapper.mapUserEntityToBean(this.userRepository.save(user));
    }

    public UserDTO updateUser(final UserDTO userDto) {
        if (userDto.getId() == null || userDto.getId() == 0L) {
            throw new InvalidRequestException();
        }
        User user = this.userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(userDto.getId().toString()));

        if (userDto.getUsername() != null && !user.getUsername().equals(userDto.getUsername())) {
            throw new IdUsernameMismatchException();
        }
        /*
         * To Override the user input values
         */
        userDto.setUsername(user.getUsername());
        userDto.setUserPassword(user.getUserPassword());
        userDto.setRole(Role.valueOf(user.getRole()));

        userModelMapper.mapUserBeanToEntity(userDto, user);

        return userModelMapper.mapUserEntityToBean(this.userRepository.save(user));
    }

    public void deleteUserById(final Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            throw new UserNotFoundException(id.toString());
        }
    }

    public void updateUserRole(final Long id, final Role role) {
        int count = userRepository.updateRole(id, role.name());
        if (count <= 0) {
            throw new UserNotFoundException(id.toString());
        }
    }

    public boolean updatePassword(final String username, final String newPassword) {
        return userRepository.updatePassword(username, newPassword) > 0;
    }

}
