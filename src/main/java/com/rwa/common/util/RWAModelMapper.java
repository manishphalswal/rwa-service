package com.rwa.common.util;

import com.rwa.referencedata.domain.StateDto;
import com.rwa.referencedata.domain.VillageDto;
import com.rwa.referencedata.entity.State;
import com.rwa.referencedata.entity.Village;
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

    public VillageDto mapVillageEntityToBean(final Village village) {
        return modelMapper.map(village, VillageDto.class);
    }

    public StateDto mapStateEntityToBean(final State state) {
        return modelMapper.map(state, StateDto.class);
    }

}
