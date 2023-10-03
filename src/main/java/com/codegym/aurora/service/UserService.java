package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.*;
import com.codegym.aurora.payload.response.MessageResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDTO;

import java.util.List;

public interface UserService {

//    User getUserFromToken();

    String login(LoginRequestDTO loginRequest);

    MessageResponseDTO register(RegisterRequestDTO registerRequest);

    MessageResponseDTO registerAccountAdmin(RegisterAdminRequestDTO registerAdminRequestDTO);

    UserResponseDTO getUserByUsername(String username);

    UserResponseDTO getUserById(long id);

    List<UserResponseDTO> getAllUser();

    List<UserResponseDTO> getAll();

    MessageResponseDTO setVip(BuyVipRequestDTO buyVipRequestDTO);

    MessageResponseDTO edit(UserInfoRequestDTO user, long id);

//    MessageResponseDTO changePassword(PasswordRequestDTO passwordRequestDTO);

    MessageResponseDTO delete(String username);

}
