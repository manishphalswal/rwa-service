package com.rwa.controller.user;

import com.rwa.domain.user.UserDto;
import com.rwa.exception.user.UserNotFoundException;
import com.rwa.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.ok(this.userService.getUserById(id));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
        try {
            this.userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ResponseStatus(value=HttpStatus.CONFLICT,
            reason="Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> conflict() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with username.");
    }
}
