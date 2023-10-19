package com.codegym.aurora.converter;

import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.response.UserResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDtoForNumerologyReport;

public interface UserConverter {

    UserResponseDTO converterEntityUserToUserInfoResponseDTO(User user, UserDetail userDetail);

    UserResponseDtoForNumerologyReport convertEntityToUserForNumerologyReportResponseDTO(User user);

}
