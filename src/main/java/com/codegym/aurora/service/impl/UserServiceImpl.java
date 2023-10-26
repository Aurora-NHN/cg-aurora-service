package com.codegym.aurora.service.impl;

import com.codegym.aurora.cache.CartCache;
import com.codegym.aurora.cache.TokenCache;
import com.codegym.aurora.converter.UserConverter;
import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.request.LoginRequestDTO;
import com.codegym.aurora.payload.request.PasswordRequestDTO;
import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.request.UserInfoRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDTO;
import com.codegym.aurora.repository.CartRepository;
import com.codegym.aurora.repository.UserDetailRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.security.JwtTokenProvider;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.util.Constant;
import com.codegym.aurora.util.ERole;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new GsonFactory();

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final TokenCache tokenCache;
    private final UserConverter userConverter;
    private final CartRepository cartRepository;
    private final CartCache cartCache;
    @Value("${client_id}")
    private String clientId;

    @Override
    public ResponseDTO login(LoginRequestDTO loginRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        User userCheck = userRepository.findByUsername(loginRequestDTO.getUsername());
        if (userCheck == null) {
            responseDTO.setMessage(Constant.USER_IS_NOT_EXISTS);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
            return responseDTO;
        } else if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userCheck.getPassword())) {
            responseDTO.setMessage(Constant.WRONG_PASSWORD);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
            return responseDTO;
        } else if (userCheck.isDelete()) {
            responseDTO.setMessage(Constant.ACCOUNT_BLOCK);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
            return responseDTO;
        }
        String token = jwtTokenProvider.generateToken(userCheck.getUsername());
        tokenCache.addToken(loginRequestDTO.getUsername(), token);
        responseDTO.setMessage(Constant.LOGIN_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(token);
        return responseDTO;
    }

    @Override
    public ResponseDTO loginAdmin(LoginRequestDTO loginRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        User userCheck = userRepository.findByUsername(loginRequestDTO.getUsername());
        if (userCheck == null) {
            responseDTO.setMessage(Constant.USER_IS_NOT_EXISTS);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
            return responseDTO;
        }
        String role = userCheck.getRole();
        if (!role.equals("ROLE_ADMIN")){
            responseDTO.setMessage(Constant.UNAUTHORIZED);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
            return responseDTO;
        }else if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userCheck.getPassword())) {
            responseDTO.setMessage(Constant.WRONG_PASSWORD);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
            return responseDTO;
        }
        String token = jwtTokenProvider.generateToken(userCheck.getUsername());
        tokenCache.addToken(loginRequestDTO.getUsername(), token);
        responseDTO.setMessage(Constant.LOGIN_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(token);
        return responseDTO;
    }

    @Override
    public ResponseDTO logout() {
        String username = getCurrentUsername();
        ResponseDTO responseDTO = new ResponseDTO();
        if (tokenCache.getToken(username) == null) {
            responseDTO.setStatus(HttpStatus.NOT_ACCEPTABLE);
            responseDTO.setMessage(Constant.LOGOUT_FAIL);
        } else {
            tokenCache.removeToken(username);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setMessage(Constant.LOGOUT_SUCCESS);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getUserInfo() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            String username = getCurrentUsername();
            User user = userRepository.findByUsername(username);
            UserResponseDTO userResponseDTO = userConverter.converterEntityUserToUserInfoResponseDTO(user,user.getUserDetail());
            responseDTO.setMessage(Constant.GET_USER_INFO_SUCCESS);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setData(userResponseDTO);
        } catch (Exception e){
            responseDTO.setMessage(Constant.GET_USER_INFO_FAIL);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO registerUser(RegisterRequestDTO registerRequest) {
        String role = "ROLE_".concat(ERole.USER.toString());
        return register(registerRequest, role);
    }

    @Override
    public ResponseDTO registerAdmin(RegisterRequestDTO registerRequest) {
        String role = "ROLE_".concat(ERole.ADMIN.toString());
        return register(registerRequest, role);
    }

    @Override
    public ResponseDTO register(RegisterRequestDTO registerRequest, String role) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            User userCheck = userRepository.findByUsername(registerRequest.getUsername());
            if (userCheck != null) {
                responseDTO.setMessage(Constant.USERNAME_EXISTS);
                responseDTO.setStatus(HttpStatus.BAD_REQUEST);
                return responseDTO;
            }
            UserDetail userDetailCheck = userDetailRepository.findByEmail(registerRequest.getEmail());
            if (userDetailCheck != null) {
                responseDTO.setMessage(Constant.EMAIL_EXISTS);
                responseDTO.setStatus(HttpStatus.BAD_REQUEST);
                return responseDTO;
            }
            String password = registerRequest.getPassword();
            String cypherText = passwordEncoder.encode(password);
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(cypherText);
            user.setRole(role);
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(user);
            userDetail.setPhoneNumber(registerRequest.getPhoneNumber());
            userDetail.setFullName(registerRequest.getFullName());
            userDetail.setGender(registerRequest.getGender());
            userDetail.setEmail(registerRequest.getEmail());
            userRepository.save(user);
            userDetailRepository.save(userDetail);
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            cartCache.addToCart(user.getId(),cart);
        } catch (Exception e) {
            responseDTO.setMessage(Constant.REGISTER_FAIL);
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
            return responseDTO;
        }
        responseDTO.setMessage(Constant.REGISTER_SUCCESS);
        responseDTO.setStatus(HttpStatus.CREATED);
        return responseDTO;
    }

    @Override
    public void updateUserPassword(String email, String tempPassword) {
        UserDetail userDetail = userDetailRepository.findByEmail(email);
        User user = userDetail.getUser();
        String hashedPassword = passwordEncoder.encode(tempPassword);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public boolean checkValidEmail(String email) {
        UserDetail userDetail = userDetailRepository.findByEmail(email);
        return userDetail != null;
    }

    @Override
    public boolean checkOldPassword(String password) {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username);
        if(user == null){
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public ResponseDTO changePassword(PasswordRequestDTO passwordRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (checkOldPassword(passwordRequestDTO.getOldPassword())) {
            String username = getCurrentUsername();
            User user = userRepository.findByUsername(username);
            String cypherPass = passwordEncoder.encode(passwordRequestDTO.getNewPassword());
            user.setPassword(cypherPass);
            userRepository.save(user);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setMessage(Constant.CHANGE_PASSWORD_SUCCESS);
        } else {
            responseDTO.setMessage(Constant.WRONG_OLD_PASSWORD);
            responseDTO.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO editInfo(UserInfoRequestDTO userInfoRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findByUsername(getCurrentUsername());
        UserDetail userDetail = user.getUserDetail();
        String email = userInfoRequestDTO.getEmail();
        if(!userDetail.getEmail().equals(email) && !checkValidEmail(email)){
            userDetail.setEmail(email);
            setUserInfo(userInfoRequestDTO);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setMessage(Constant.EDIT_INFO_SUCCESS);
            return responseDTO;
        } else if (userDetail.getEmail().equals(email)) {
            setUserInfo(userInfoRequestDTO);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setMessage(Constant.EDIT_INFO_SUCCESS);
            return responseDTO;
        }
        responseDTO.setStatus(HttpStatus.BAD_REQUEST);
        responseDTO.setMessage(Constant.EMAIL_EXISTS);
        return responseDTO;
    }

    private void setUserInfo(UserInfoRequestDTO userInfoRequestDTO) {
        User user = userRepository.findByUsername(getCurrentUsername());
        UserDetail userDetail = user.getUserDetail();
        userDetail.setGender(userInfoRequestDTO.getGender());
        userDetail.setPhoneNumber(userInfoRequestDTO.getPhoneNumber());
        userDetail.setFullName(userInfoRequestDTO.getFullName());
        userRepository.save(user);
        userDetailRepository.save(userDetail);
    }

    @Override
    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public ResponseDTO googleAuthenticate(String credential) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                .setAudience(Collections.singletonList(clientId))
                .build();
        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(credential);
        }catch (Exception exception){
            return new ResponseDTO("Authentication error!", HttpStatus.SERVICE_UNAVAILABLE, null);
        }

        if (idToken == null)
            return new ResponseDTO("Authentication failed!", HttpStatus.BAD_REQUEST, null);

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String userId = payload.getSubject();
        String name = (String) payload.get("name");

        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findByUserDetailEmail(email);
        if (user == null){
            UserDetail userDetail = UserDetail.builder()
                    .fullName(name)
                    .email(email)
                    .build();
            User newUser = User.builder()
                    .username("user" + userId)
                    .role("ROLE_".concat(ERole.USER.toString()))
                    .userDetail(userDetail)
                    .build();
            userDetail.setUser(newUser);
            userRepository.save(newUser);
            String token = jwtTokenProvider.generateToken(newUser.getUsername());
            tokenCache.addToken(newUser.getUsername(), token);
            responseDTO.setMessage(Constant.LOGIN_SUCCESS);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setData(token);
        }else {
            String token = jwtTokenProvider.generateToken(user.getUsername());
            tokenCache.addToken(user.getUsername(), token);
            responseDTO.setMessage(Constant.LOGIN_SUCCESS);
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setData(token);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getCountOfUser() {
        User user = userRepository.findByUsername(getCurrentUsername());
        int count = user.getCount();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.GET_COUNT_SUCCESS);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(count);
        return responseDTO;
    }
}