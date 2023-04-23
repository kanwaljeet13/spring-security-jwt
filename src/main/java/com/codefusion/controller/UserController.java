package com.codefusion.controller;

import com.codefusion.model.dto.request.UserRequestDto;
import com.codefusion.model.dto.response.UserResponseDto;
import com.codefusion.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {
    
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestBody) {
        return ResponseEntity.ok(userService.createUser(requestBody));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
