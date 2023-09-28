package com.codegym.aurora.back_office.converter;

import com.codegym.aurora.back_office.entity.User;
import com.codegym.aurora.back_office.payload.response.UserResponseDTO;

public interface UserConverter {

    UserResponseDTO converterEntityUserToUserInfoResponseDTO(User user);

}
