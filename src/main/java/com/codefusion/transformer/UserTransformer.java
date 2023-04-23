package com.codefusion.transformer;

import com.codefusion.model.entity.User;
import com.codefusion.model.dto.request.UserRequestDto;
import com.codefusion.model.dto.response.UserResponseDto;
import com.codefusion.util.CommonUtils;
import org.springframework.beans.BeanUtils;

public class UserTransformer {

    private UserTransformer() {
    }

    public static User toUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(CommonUtils.getEncodedPassword(userRequestDto.getPassword()));
        user.setRole(userRequestDto.getRole());
        return user;
    }

    public static UserResponseDto toUserDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }
}
