package com.rwa.user.controller;

import com.rwa.user.domain.UserDto;
import com.rwa.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @GetMapping("user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") final String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userService.saveUser(userDto));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final Long id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
