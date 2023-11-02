package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.LoginRequestDTO;
import com.codegym.aurora.payload.request.PasswordRequestDTO;
import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.request.UserInfoRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.response.UserAdminResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    ResponseDTO login (LoginRequestDTO loginRequestDTO);

    ResponseDTO loginAdmin(LoginRequestDTO loginRequestDTO);

    ResponseDTO logout();

    ResponseDTO getUserInfo();

    ResponseDTO register(RegisterRequestDTO registerRequest);

    ResponseDTO setRoleAdmin(String username);

    ResponseDTO setRoleUser(String username);

    void updateUserPassword(String email, String tempPassword);

    boolean checkOldPassword(String password);

    ResponseDTO changePassword(PasswordRequestDTO passwordRequestDTO);

    ResponseDTO editInfo(UserInfoRequestDTO userInfoRequestDTO);

    boolean checkValidEmail(String email);

    public String getCurrentUsername();

    ResponseDTO googleAuthenticate(String credential);

    ResponseDTO getCountOfUser();

    ResponseDTO deleteUser(String username);

    Page<UserAdminResponseDTO> getUserListByPage(Pageable pageable, String username);
}
