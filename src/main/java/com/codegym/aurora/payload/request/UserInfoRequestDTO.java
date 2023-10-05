package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequestDTO {

    private String username;

    private String fullName;

    private String gender;

    private String phoneNumber;

    private String email;

    private MultipartFile imageUrl;
}