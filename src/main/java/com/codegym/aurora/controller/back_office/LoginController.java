package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.request.LoginRequestDTO;
import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        ResponseDTO responseDto = userService.login(loginRequestDTO);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @PostMapping(value = "/register-user")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        ResponseDTO responseDto = userService.registerUser(registerRequestDTO);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @PostMapping(value = "/register-admin")
    public ResponseEntity<ResponseDTO> registerAdmin(@RequestBody RegisterRequestDTO registerRequestDTO) {
        ResponseDTO responseDto = userService.registerAdmin(registerRequestDTO);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<ResponseDTO> logout() {
        ResponseDTO responseDto = userService.logout();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/google-login")
    public ResponseEntity<ResponseDTO> googleLogin(@RequestBody String credential) {
        ResponseDTO responseDto = userService.googleAuthenticate(credential);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }
}
