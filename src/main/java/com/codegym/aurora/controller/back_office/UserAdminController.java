package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.response.UserAdminResponseDTO;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class UserAdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<UserAdminResponseDTO> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
