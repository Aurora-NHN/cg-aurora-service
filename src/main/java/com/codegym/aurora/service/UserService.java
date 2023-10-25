package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.LoginRequestDTO;
import com.codegym.aurora.payload.request.PasswordRequestDTO;
import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.request.UserInfoRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

public interface UserService {

    ResponseDTO login (LoginRequestDTO loginRequestDTO);

    ResponseDTO loginAdmin(LoginRequestDTO loginRequestDTO);

    ResponseDTO logout();

    ResponseDTO getUserInfo();

    ResponseDTO registerUser(RegisterRequestDTO registerRequest);

    ResponseDTO registerAdmin(RegisterRequestDTO registerRequest);

    ResponseDTO register(RegisterRequestDTO registerRequest, String role);

    void updateUserPassword(String email, String tempPassword);

    boolean checkOldPassword(String password);

    ResponseDTO changePassword(PasswordRequestDTO passwordRequestDTO);

    ResponseDTO editInfo(UserInfoRequestDTO userInfoRequestDTO);

    boolean checkValidEmail(String email);

    public String getCurrentUsername();

    ResponseDTO googleAuthenticate(String credential);

    ResponseDTO getCountOfUser();
}
