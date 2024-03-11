package com.rwa.common.util.mapper;

import com.rwa.common.domain.Role;
import com.rwa.referencedata.domain.StateDTO;
import com.rwa.referencedata.domain.VillageDTO;
import com.rwa.referencedata.entity.State;
import com.rwa.referencedata.entity.Village;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.domain.UserSessionDTO;
import com.rwa.user.entity.User;
import com.rwa.user.entity.UserSession;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserModelMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    public void init() {
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

    public UserSessionDTO mapObjectArrayToUserSessionDTOBean(final Object[] dtoObjArr) {
        return UserSessionDTO.builder()
                    .id((Long) dtoObjArr[0])
                    .username((String) dtoObjArr[1])
                    .userPassword((String) dtoObjArr[2])
                    .role(Role.valueOf((String) dtoObjArr[3]))
                    .token((String) dtoObjArr[4])
                    .active((boolean) dtoObjArr[5])
                    .locked((boolean) dtoObjArr[6])
                    .credExpired((boolean) dtoObjArr[7])
                    .loginTime(Optional.ofNullable(dtoObjArr[9]).map((obj) -> ((Timestamp) dtoObjArr[9]).toLocalDateTime()).orElseGet(() -> null))
                    .lastLoginTime(Optional.ofNullable(dtoObjArr[10]).map((obj) -> ((Timestamp) dtoObjArr[10]).toLocalDateTime()).orElseGet(() -> null))
                    .build();
    }

}
