package com.codegym.aurora.back_office.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequestDTO {

    private String username;

    private String fullName;

    private String gender;

    private String phoneNumber;

    private String email;

    private String imageUrl;
}
