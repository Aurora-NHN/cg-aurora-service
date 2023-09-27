package com.codegym.aurora.backOffice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {

    private String username;

    private String password;
}
