package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequestDTO {

    private String username;

    private String password;

    private String fullName;

    private String gender;

    private String phoneNumber;

    private String email;
}
