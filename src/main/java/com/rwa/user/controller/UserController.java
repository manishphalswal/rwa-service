package com.rwa.user.controller;

import com.rwa.common.domain.Role;
import com.rwa.user.domain.UserDTO;
import com.rwa.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @GetMapping("user/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") final String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto) {
        userDto.setUserPassword(passwordEncoder.encode(new String(Base64.getDecoder().decode(userDto.getUserPassword()))));
        return ResponseEntity.ok(this.userService.saveUser(userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto) {
        userDto.setUserPassword(passwordEncoder.encode(new String(Base64.getDecoder().decode(userDto.getUserPassword()))));
        return ResponseEntity.ok(this.userService.saveUser(userDto));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto) {
        return ResponseEntity.ok(this.userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final Long id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/role/{role}")
    public ResponseEntity<Void> assignRole(
            @PathVariable("id") final Long id,
            @PathVariable("role") final String role) {

        boolean admin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equalsIgnoreCase(Role.ADMIN.getAuthority()));

        if (admin) {
            this.userService.updateUserRole(id, Role.valueOf(role.toUpperCase()));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
