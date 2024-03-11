package com.rwa.security.controller;

import com.rwa.security.domain.ChangePassword;
import com.rwa.security.domain.JwtRequest;
import com.rwa.security.domain.JwtResponse;
import com.rwa.security.domain.ResetPassword;
import com.rwa.security.util.JwtTokenUtil;
import com.rwa.user.exception.UserNotFoundException;
import com.rwa.user.service.UserService;
import com.rwa.user.service.UserSessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Objects;

/**
 * All operations are username driven
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService jwtInMemoryUserDetailsService;

    private final UserSessionService userSessionService;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        if (authentication.isAuthenticated()) {
            userSessionService.updateLoginDetails(authenticationRequest.getUsername(), token);
        }

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/logout/{username}")
    public ResponseEntity<String> logout(@PathVariable("username") String username) {
        String loggedInUsername = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if (!loggedInUsername.equalsIgnoreCase(username)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized to access this resource");
        }
        userSessionService.updateLogoutDetails(username);
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logout successful");
    }

    @PostMapping("/password/change")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePassword changePassword) {
        changePassword.setNewPassword(passwordEncoder.encode(new String(Base64.getDecoder().decode(changePassword.getNewPassword()))));
        changePassword.setOldPassword(new String(Base64.getDecoder().decode(changePassword.getOldPassword())));

        userService.changePassword(changePassword);
        userSessionService.updateLogoutDetails(changePassword.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password/reset")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPassword resetPassword) {

        resetPassword.setNewPassword(passwordEncoder.encode(new String(Base64.getDecoder().decode(resetPassword.getNewPassword()))));

        userService.resetPassword(resetPassword);
        userSessionService.updateLogoutDetails(resetPassword.getUsername());
        return ResponseEntity.ok(Boolean.TRUE);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, new String(Base64.getDecoder().decode(password))));
        } catch (DisabledException e) {
            log.error("USER_DISABLED: " + username);
            throw e;
        } catch (BadCredentialsException e) {
            log.error("INVALID_CREDENTIALS: " + username);
            throw e;
        } catch (InternalAuthenticationServiceException e) {
            if (e.getMessage() != null && e.getMessage().contains("User not found for Id")) {
                throw new UserNotFoundException(username);
            }
            throw e;
        }
    }
}
