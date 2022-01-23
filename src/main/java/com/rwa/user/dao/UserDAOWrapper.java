package com.rwa.user.dao;

import com.rwa.common.domain.Role;
import com.rwa.common.util.RWAModelMapper;
import com.rwa.exception.InvalidRequestException;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.entity.User;
import com.rwa.user.exception.IdUsernameMismatchException;
import com.rwa.user.exception.UserNotFoundException;
import com.rwa.user.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserDAOWrapper {

    private final IUserRepository userRepository;
    
    private final RWAModelMapper rwaModelMapper;

    public List<UserDTO> getUsers() {
        return this.userRepository.findAll()
                .parallelStream()
                .map(rwaModelMapper::mapUserEntityToBean)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(final Long id) {
        return rwaModelMapper.mapUserEntityToBean(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString())));
    }

    public UserDTO getUserByUsername(final String username) {
        return rwaModelMapper.mapUserEntityToBean(this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    public UserDTO saveUser(final UserDTO userDto, final String createdBy) {
        User user = rwaModelMapper.mapUserBeanToEntity(userDto);
        user.setCreatedBy(createdBy);
        user.getAddress().setCreatedBy(createdBy);
        return rwaModelMapper.mapUserEntityToBean(this.userRepository.save(user));
    }

    public UserDTO updateUser(final UserDTO userDto) {
        if (userDto.getId() == null || userDto.getId() == 0L) {
            throw new InvalidRequestException();
        }
        User user = this.userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(userDto.getId().toString()));

        if (userDto.getUsername() != null && !user.getUsername().equals(userDto.getUsername())) {
            throw new IdUsernameMismatchException();
        }
        userDto.setUsername(user.getUsername()); // To override if null in request
        rwaModelMapper.mapUserBeanToEntity(userDto, user);

        return rwaModelMapper.mapUserEntityToBean(this.userRepository.save(user));
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

}
