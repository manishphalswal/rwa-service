package com.rwa.user.domain;

import com.rwa.common.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSessionDTO {
    private Long id;

    private String username;
    private String userPassword;

    private Role role;

    private boolean loggedIn;
    private boolean active;
    private boolean locked;
    private boolean credExpired;

    private OffsetDateTime lastLogin;
    private OffsetDateTime loginTime;
}
