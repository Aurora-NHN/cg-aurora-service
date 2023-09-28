package com.codegym.aurora.back_office.service;

import com.codegym.aurora.back_office.entity.User;
import com.codegym.aurora.back_office.payload.request.*;
import com.codegym.aurora.back_office.payload.response.MessageResponseDTO;
import com.codegym.aurora.back_office.payload.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    String login(LoginRequestDTO loginRequest);

    MessageResponseDTO register(RegisterRequestDTO registerRequest);

    MessageResponseDTO registerAccountAdmin(RegisterAdminRequestDTO registerAdminRequestDTO);

    UserResponseDTO getUserByUsername(String username);

    UserResponseDTO getUserById(long id);

    List<UserResponseDTO> getAllUser();

    List<UserResponseDTO> getAll();

    MessageResponseDTO setVip(BuyVipRequestDTO buyVipRequestDTO);

    MessageResponseDTO edit(UserInfoRequestDTO user, long id);

    MessageResponseDTO delete(String username);

}
