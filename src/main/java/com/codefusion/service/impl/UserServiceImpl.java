package com.codefusion.service.impl;

import com.codefusion.model.dto.request.UserRequestDto;
import com.codefusion.model.dto.response.UserResponseDto;
import com.codefusion.model.entity.User;
import com.codefusion.repository.UserRepository;
import com.codefusion.transformer.UserTransformer;
import com.codefusion.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userRepository.save(UserTransformer.toUser(userRequestDto));
        return UserTransformer.toUserDto(user);
    }

    @Override
    public UserResponseDto getUser(long id) {
        return userRepository.findById(id).map(UserTransformer::toUserDto).orElseThrow(
                () -> new RuntimeException("User not found by id " + id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found by username " + username));
    }
}
