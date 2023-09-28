package com.codegym.aurora.back_office.converter.impl;

import com.codegym.aurora.back_office.converter.UserConverter;
import com.codegym.aurora.back_office.entity.User;
import com.codegym.aurora.back_office.payload.response.UserResponseDTO;
import org.springframework.beans.BeanUtils;

public class UserConverterImpl implements UserConverter {

    @Override
    public UserResponseDTO converterEntityUserToUserInfoResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(userResponseDTO,user);
        return userResponseDTO;
    }
}
