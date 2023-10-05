package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.LoginRequestDTO;
import com.codegym.aurora.payload.request.PasswordRequestDTO;
import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

public interface UserService {

    ResponseDTO login (LoginRequestDTO loginRequestDTO);

    ResponseDTO logout();

    ResponseDTO registerUser(RegisterRequestDTO registerRequest);

    ResponseDTO registerAdmin(RegisterRequestDTO registerRequest);

    ResponseDTO register(RegisterRequestDTO registerRequest, String role);

    void updateUserPassword(String email, String tempPassword);

    boolean checkValidEmail(String email);

    boolean checkOldPassword(String password);

    ResponseDTO changePassword(PasswordRequestDTO passwordRequestDTO);
}
