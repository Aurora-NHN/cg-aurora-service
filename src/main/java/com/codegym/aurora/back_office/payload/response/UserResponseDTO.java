package com.codegym.aurora.back_office.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String username;

    private String fullName;

    private int count;

    private boolean isVip;

    private String gender;

    private String phoneNumber;

    private String email;

    private String imageUrl;
}
