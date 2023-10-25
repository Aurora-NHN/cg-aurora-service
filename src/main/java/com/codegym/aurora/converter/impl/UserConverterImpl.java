package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.UserConverter;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.response.UserResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDtoForNumerologyReport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    @Override
    public UserResponseDTO converterEntityUserToUserInfoResponseDTO(User user, UserDetail userDetail) {
        return UserResponseDTO.builder()
                .fullName(userDetail.getFullName())
                .email(userDetail.getEmail())
                .gender(userDetail.getGender())
                .isVip(user.isVip())
                .phoneNumber(userDetail.getPhoneNumber())
                .username(user.getUsername())
                .build();
    }

    @Override
    public UserResponseDtoForNumerologyReport convertEntityToUserForNumerologyReportResponseDTO(User user) {
        return new UserResponseDtoForNumerologyReport(user.getId(), user.getCount(), user.isVip());
    }
}
