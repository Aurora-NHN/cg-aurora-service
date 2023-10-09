package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequestDTO {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @Pattern(
            regexp = "^\\d{10}$",
            message = "Phone number must be 10 digits long"
    )
    private String phoneNumber;

    @Email(message = "Invalid email address")
    private String email;

}