package com.codegym.aurora.converter;

import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.response.UserResponseDTO;

public interface UserConverter {

    UserResponseDTO converterEntityUserToUserInfoResponseDTO(User user);

}
