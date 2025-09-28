package com.maxvpire.users.users;

import com.maxvpire.users.users.dto.PasswordCheckRequest;
import com.maxvpire.users.users.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUserByUserName(username));
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<UserResponse>> searchUsersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.searchUsers(username));
    }

    @GetMapping("/main/id/{id}")
    public ResponseEntity<User> getUsersByMainId(@PathVariable String id) {
        return ResponseEntity.ok(userService.findByMainId(id));
    }

    @PutMapping("/name/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody String username) {
        userService.updateUsername(id, username);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<?> updateUserPassword(@PathVariable String id, @Valid @RequestBody PasswordCheckRequest request) {
        userService.updatePassword(id, request);
        return ResponseEntity.accepted().build();
    }

}
