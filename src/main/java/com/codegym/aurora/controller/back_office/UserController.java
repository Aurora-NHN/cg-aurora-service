package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.request.PasswordRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PutMapping("/change-password")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody PasswordRequestDTO passwordRequestDTO){
        return null;
    }
}
