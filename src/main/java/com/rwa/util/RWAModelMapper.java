package com.rwa.util;

import com.rwa.domain.user.UserDto;
import com.rwa.entity.user.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RWAModelMapper {
    private final ModelMapper modelMapper;

    public User mapUserBeanToEntity(final UserDto userDto) {
        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getAddress().getId());
            }
        });
        return modelMapper.map(userDto, User.class);
    }

    public User mapUserBeanToEntity(final UserDto userDto, final User user) {
        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                skip(destination.getAddress().getId());
            }
        });
        modelMapper.map(userDto, user);
        return user;
    }

    public UserDto mapUserEntityToBean(final User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto mapUserEntityToBean(final User user, final UserDto userDto) {
        modelMapper.map(user, userDto);
        return userDto;
    }

}
