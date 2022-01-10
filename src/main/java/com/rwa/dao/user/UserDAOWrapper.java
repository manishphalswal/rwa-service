package com.rwa.dao.user;

import com.rwa.domain.user.UserDto;
import com.rwa.entity.user.User;
import com.rwa.exception.user.UserAlreadyExistsException;
import com.rwa.exception.user.UserNotFoundException;
import com.rwa.repository.user.IUserRepository;
import com.rwa.util.RWAModelMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<UserDto> getUsers() {
        return this.userRepository.findAll()
                .parallelStream()
                .map(rwaModelMapper::mapUserEntityToBean)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(final Long id) {
        return rwaModelMapper.mapUserEntityToBean(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString())));
    }

    public UserDto saveUser(final UserDto userDto) {
        return rwaModelMapper.mapUserEntityToBean(this.userRepository.save(convertBeanToEntity(userDto)));
    }

    public UserDto updateUser(final UserDto userDto) {
        User user = this.userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(userDto.getId().toString()));

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

    private User convertBeanToEntity(final UserDto userDto) {
        log.info("Received Document with Details: " + userDto);

        User user = rwaModelMapper.mapUserBeanToEntity(userDto);
        /**
         * TODO: replace with user logged in
         */
        user.setCreatedBy("manish");
        user.getAddress().setCreatedBy("manish");

        log.info("Saving Document with Details: " + user);
        return user;
    }

}
