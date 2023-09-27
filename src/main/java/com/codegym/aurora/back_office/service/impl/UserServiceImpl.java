package com.codegym.aurora.back_office.service.impl;

import com.codegym.aurora.back_office.entity.User;
import com.codegym.aurora.back_office.payload.request.LoginRequestDTO;
import com.codegym.aurora.back_office.payload.request.RegisterRequestDTO;
import com.codegym.aurora.back_office.payload.request.UserInfoRequestDTO;
import com.codegym.aurora.back_office.repository.UserDetailRepository;
import com.codegym.aurora.back_office.repository.UserRepository;
import com.codegym.aurora.back_office.service.UserService;
import com.codegym.aurora.cache.TokenCache;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private TokenCache tokenCache;

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private UserDetailRepository userDetailRepository;

    @Override
    public String login(LoginRequestDTO authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername());

        if(!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())){
            throw new AuthenticationServiceException("Wrong password");
        }
//        return tokenCache.;
        return null;
    }

    @Override
    public User register(RegisterRequestDTO registerRequest) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void edit(UserInfoRequestDTO user) {

    }

    @Override
    public void delete(String username) {

    }
}
