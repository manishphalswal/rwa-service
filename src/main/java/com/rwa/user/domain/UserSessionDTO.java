package com.rwa.user.domain;

import com.rwa.common.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSessionDTO {
    private Long id;

    private String username;
    private String userPassword;

    private Role role;

    private String token;
    private boolean active;
    private boolean locked;
    private boolean credExpired;

    private LocalDateTime lastLoginTime;
    private LocalDateTime  loginTime;
}
