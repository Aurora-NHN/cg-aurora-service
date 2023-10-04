package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordRequestDTO {

    private String newPassword;

    private String oldPassword;
}
