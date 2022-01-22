package com.rwa.security.controller;

import com.rwa.security.domain.JwtRequest;
import com.rwa.security.domain.JwtResponse;
import com.rwa.security.util.JwtTokenUtil;
import com.rwa.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService jwtInMemoryUserDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, new String(Base64.getDecoder().decode(password))));
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
