package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.UserConverter;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.request.*;
import com.codegym.aurora.payload.response.MessageResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDTO;
import com.codegym.aurora.repository.UserDetailRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.cache.TokenCache;
import com.codegym.aurora.security.JwtAuthFilter;
import com.codegym.aurora.security.JwtTokenProvider;
import com.codegym.aurora.util.Constant;
import com.codegym.aurora.util.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtAuthFilter jwtAuthFilter;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserDetailRepository userDetailRepository;

    private final TokenCache tokenCache;

    private UserConverter userConverter;

//    @Override
//    public User getUserFromToken() {
//        String token =
//        String username = jwtTokenProvider.getUsernameFromJWT(token);
//        return userRepository.findByUsername(username);
//    }

    @Override
    public String login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        String token = jwtTokenProvider.generateToken(user);
        tokenCache.addToken(loginRequest.getUsername(),token);
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationServiceException("Wrong password");
        }
        return token;
    }

    @Override
    public MessageResponseDTO register(RegisterRequestDTO registerRequest) {
        try {
            String password = registerRequest.getPassword();
            String cypherText = passwordEncoder.encode(password);
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(cypherText);
            user.setRole("ROLE_".concat(ERole.USER.toString()));
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(user);
            userDetail.setPhoneNumber(registerRequest.getPhoneNumber());
            userDetail.setFullName(registerRequest.getFullName());
            userDetail.setGender(registerRequest.getGender());
            userDetail.setEmail(registerRequest.getEmail());
            userRepository.save(user);
            userDetailRepository.save(userDetail);
        } catch (Exception e) {
            return new MessageResponseDTO(Constant.REGISTER_FAIL);
        }
        return new MessageResponseDTO(Constant.REGISTER_SUCCESS);
    }

    @Override
    public MessageResponseDTO registerAccountAdmin(RegisterAdminRequestDTO registerAdminRequestDTO) {
        try {
            String password = registerAdminRequestDTO.getPassword();
            String cypherText = passwordEncoder.encode(password);
            User user = new User();
            user.setUsername(registerAdminRequestDTO.getUsername());
            user.setPassword(cypherText);
            user.setRole("ROLE_".concat(ERole.ADMIN.toString()));
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(user);
            userDetail.setPhoneNumber(registerAdminRequestDTO.getPhoneNumber());
            userDetail.setFullName(registerAdminRequestDTO.getFullName());
            userDetail.setGender(registerAdminRequestDTO.getGender());
            userDetail.setEmail(registerAdminRequestDTO.getEmail());
            userRepository.save(user);
            userDetailRepository.save(userDetail);
        } catch (Exception e) {
            return new MessageResponseDTO(Constant.REGISTER_FAIL);
        }
        return new MessageResponseDTO(Constant.REGISTER_SUCCESS);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userConverter.converterEntityUserToUserInfoResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserById(long id) {
        User user = userRepository.findUserById(id);
        return userConverter.converterEntityUserToUserInfoResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user: userList){
            if(user.getRole().equals("ROLE_USER")) {
                UserResponseDTO userResponseDTO = userConverter.converterEntityUserToUserInfoResponseDTO(user);
                userResponseDTOList.add(userResponseDTO);
            }
        }
        return userResponseDTOList;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user: userList){
            UserResponseDTO userResponseDTO = userConverter.converterEntityUserToUserInfoResponseDTO(user);
            userResponseDTOList.add(userResponseDTO);
        }
        return userResponseDTOList;
    }

    @Override
    public MessageResponseDTO setVip(BuyVipRequestDTO buyVipRequestDTO) {
        try {
            String username = jwtTokenProvider.getUsernameFromJWT(buyVipRequestDTO.getToken());
            User user = userRepository.findByUsername(username);
            user.setVip(true);
            user.setCount(buyVipRequestDTO.getCount());
            userRepository.save(user);
        } catch (Exception e){
            return new MessageResponseDTO(Constant.BUY_VIP_FAIL);
        }
        return new MessageResponseDTO(Constant.BUY_VIP_SUCCESS);
    }

    @Override
    public MessageResponseDTO edit(UserInfoRequestDTO userInfoRequestDTO, long id) {
        try{
            User user = userRepository.findUserById(id);
            user.setUsername(userInfoRequestDTO.getUsername());
            UserDetail userDetail = user.getUserDetail();
            userDetail.setEmail(userInfoRequestDTO.getEmail());
            userDetail.setPhoneNumber(userInfoRequestDTO.getPhoneNumber());
            userDetail.setGender(userInfoRequestDTO.getGender());
            userDetail.setImageUrl(userInfoRequestDTO.getImageUrl());
            userDetail.setFullName(userInfoRequestDTO.getFullName());
            userRepository.save(user);
            userDetailRepository.save(userDetail);
        } catch (Exception e){
            return new MessageResponseDTO(Constant.EDIT_FAIL);
        }
        return new MessageResponseDTO(Constant.EDIT_SUCCESS);
    }

//    @Override
//    public MessageResponseDTO changePassword(PasswordRequestDTO passwordRequestDTO) {
//        try {
//            User user = getUserFromToken()
//        }
//        return null;
//    }


    @Override
    public MessageResponseDTO delete(String username) {
        try {
            User user = userRepository.findByUsername(username);
            user.setActivated(false);
            userRepository.save(user);
        } catch (Exception e){
            return new MessageResponseDTO(Constant.DELETE_FAIL);
        }
        return new MessageResponseDTO(Constant.DELETE_SUCCESS);
    }

}