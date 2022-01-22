package com.rwa.common.util;

import com.rwa.referencedata.domain.StateDTO;
import com.rwa.referencedata.domain.VillageDTO;
import com.rwa.referencedata.entity.State;
import com.rwa.referencedata.entity.Village;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.domain.UserSessionDTO;
import com.rwa.user.entity.User;
import com.rwa.user.entity.UserSession;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class RWAModelMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public RWAModelMapper() {
        modelMapper.addMappings(new PropertyMap<UserDTO, User>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getAddress().getId());
            }
        });
    }

    public User mapUserBeanToEntity(final UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public User mapUserBeanToEntity(final UserDTO userDto, final User user) {
        modelMapper.map(userDto, user);
        return user;
    }

    public UserDTO mapUserEntityToBean(final User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO mapUserEntityToBean(final User user, final UserDTO userDto) {
        modelMapper.map(user, userDto);
        return userDto;
    }

    public VillageDTO mapVillageEntityToBean(final Village village) {
        return modelMapper.map(village, VillageDTO.class);
    }

    public StateDTO mapStateEntityToBean(final State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    public UserSession mapUserSessionBeanToEntity(final UserSessionDTO userSessionDTO) {
        return modelMapper.map(userSessionDTO, UserSession.class);
    }

    public UserSessionDTO mapUserSessionEntityToBean(final UserSession userSession) {
        return modelMapper.map(userSession, UserSessionDTO.class);
    }

}
