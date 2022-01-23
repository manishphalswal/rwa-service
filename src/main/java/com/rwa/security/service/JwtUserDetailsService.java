package com.rwa.security.service;

import com.rwa.common.domain.Role;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.domain.UserSessionDTO;
import com.rwa.user.service.UserService;
import com.rwa.user.service.UserSessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final UserSessionService userSessionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return Optional.of(userSessionService.getUserSessionByUsername(username))
                    .map(userSessionDTO ->
                            User.builder()
                                    .username(userSessionDTO.getUsername())
                                    .password(userSessionDTO.getUserPassword())
                                    .disabled(!userSessionDTO.isActive())
                                    .accountLocked(userSessionDTO.isLocked())
                                    .credentialsExpired(userSessionDTO.isCredExpired())
                                    .roles(Optional.ofNullable(userSessionDTO.getRole()).orElse(Role.BASIC).name())
                                    .build())
                    .get();
        } catch (UsernameNotFoundException exception) {
            log.info("First time login for user: " + username);
        }

        UserDTO userDTO = userService.getUserByUsername(username);
        return Optional.of(userSessionService.saveUserSession(
                UserSessionDTO.builder()
                        .username(username)
                        .userPassword(userDTO.getUserPassword())
                        .role(userDTO.getRole())
                        .build()))
                .map(userSessionDTO ->
                        User.builder()
                                .username(userSessionDTO.getUsername())
                                .password(userSessionDTO.getUserPassword())
                                .accountExpired(userSessionDTO.isActive())
                                .accountLocked(userSessionDTO.isLocked())
                                .credentialsExpired(userSessionDTO.isCredExpired())
                                .roles(Optional.ofNullable(userSessionDTO.getRole()).orElse(Role.BASIC).name())
                                .build())
                .get();
    }
}