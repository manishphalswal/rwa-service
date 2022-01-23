package com.rwa.security.controller;

import com.rwa.security.domain.ChangePassword;
import com.rwa.security.domain.JwtRequest;
import com.rwa.security.domain.JwtResponse;
import com.rwa.security.util.JwtTokenUtil;
import com.rwa.user.exception.UserNotFoundException;
import com.rwa.user.service.UserSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService jwtInMemoryUserDetailsService;

    private final UserSessionService userSessionService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

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
        userSessionService.updateLogoutStatus(username);
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logout successful");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePassword changePassword) {


        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, new String(Base64.getDecoder().decode(password))));
            if (authentication.isAuthenticated()) {
                userSessionService.updateLoginStatus(username);
            }
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (InternalAuthenticationServiceException e) {
            if (e.getMessage() != null && e.getMessage().contains("User not found for Id")) {
                throw new UserNotFoundException(username);
            }
            throw e;
        }
    }
}
