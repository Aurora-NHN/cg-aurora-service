package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.UserConverter;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.response.UserResponseDTO;
import org.springframework.beans.BeanUtils;

public class UserConverterImpl implements UserConverter {

    @Override
    public UserResponseDTO converterEntityUserToUserInfoResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(userResponseDTO,user);
        return userResponseDTO;
    }
}
