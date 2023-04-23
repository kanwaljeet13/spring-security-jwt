package com.codefusion.service;

import com.codefusion.model.dto.request.UserRequestDto;
import com.codefusion.model.dto.response.UserResponseDto;
import com.codefusion.model.entity.User;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(long id);

    User findByUsername(String username);
}
