package com.rwa.common.util;

import com.rwa.user.domain.UserDto;
import com.rwa.user.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class RWAModelMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public RWAModelMapper() {
        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getAddress().getId());
                skip(destination.getAddress().getState().getId());
                skip(destination.getAddress().getVillage().getId());
            }
        });
    }

    public User mapUserBeanToEntity(final UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public User mapUserBeanToEntity(final UserDto userDto, final User user) {
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
