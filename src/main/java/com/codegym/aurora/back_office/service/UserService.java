package com.codegym.aurora.back_office.service;

import com.codegym.aurora.back_office.entity.User;
import com.codegym.aurora.back_office.payload.request.LoginRequestDTO;
import com.codegym.aurora.back_office.payload.request.RegisterRequestDTO;
import com.codegym.aurora.back_office.payload.request.UserInfoRequestDTO;

import java.util.List;

public interface UserService {
    String login(LoginRequestDTO authenticationRequest);

    User register(RegisterRequestDTO registerRequest);

    User getUserByUsername(String username);

    User getUserById(long id);

    List<User> getAllUser();

    List<User> getAll();

    void edit(UserInfoRequestDTO user);

    void delete(String username);
}
