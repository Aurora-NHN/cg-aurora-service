package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.request.PasswordRequestDTO;
import com.codegym.aurora.payload.request.UserInfoRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.sdi.ClientSdi;
import com.codegym.aurora.service.ClientService;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final ClientService clientService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getUserInfo() {
        ResponseDTO responseDTO = userService.getUserInfo();
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody PasswordRequestDTO passwordRequestDTO) {
        ResponseDTO responseDTO = userService.changePassword(passwordRequestDTO);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody ClientSdi sdi) {
        if (userService.checkValidEmail(sdi.getEmail())) {
            return ResponseEntity.ok(clientService.create(sdi));
        } else {
            return new ResponseEntity<>(Constant.EMAIL_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> editInfo(@RequestBody UserInfoRequestDTO userInfoRequestDTO) {
        ResponseDTO responseDTO = userService.editInfo(userInfoRequestDTO);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
    @GetMapping ("/count")
    public ResponseEntity<ResponseDTO> getCountUser(){
        ResponseDTO responseDTO = userService.getCountOfUser();
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
}
