package com.codegym.aurora.backOffice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {

    private String username;

    private String password;

    private String fullName;

    private String gender;

    private String phoneNumber;

    private String email;
}
