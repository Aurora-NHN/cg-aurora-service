package com.codegym.aurora.controller.back_office.non_authenticated;

import com.codegym.aurora.payload.request.LoginRequestDTO;
import com.codegym.aurora.payload.request.RegisterAdminRequestDTO;
import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.response.JwtResponseDTO;
import com.codegym.aurora.payload.response.MessageResponseDTO;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequestDTO){
        try {
            String token = userService.login(loginRequestDTO);

            return new ResponseEntity<>(new JwtResponseDTO(token), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(Constant.LOGIN_FAIL, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/register-user")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        MessageResponseDTO message = userService.register(registerRequestDTO);
        if(message.getMessage().equals(Constant.REGISTER_SUCCESS)) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/register-admin")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterAdminRequestDTO registerRequestDTO){
        MessageResponseDTO message = userService.registerAccountAdmin(registerRequestDTO);
        if(message.getMessage().equals(Constant.REGISTER_SUCCESS)) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
