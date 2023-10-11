package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private String username;

    private String fullName;

    private boolean isVip;

    private String gender;

    private String phoneNumber;

    private String email;
}
