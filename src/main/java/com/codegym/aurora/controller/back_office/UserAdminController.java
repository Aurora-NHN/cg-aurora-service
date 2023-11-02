package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class UserAdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Pageable pageable, @RequestParam(name = "username") String username){
        return new ResponseEntity<>(userService.getUserListByPage(pageable, username), HttpStatus.OK);
    }

    @GetMapping("/change-role")
    public ResponseEntity<ResponseDTO> setRole(@RequestParam String username){
        ResponseDTO responseDTO = userService.changeRole(username);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

    @GetMapping("/delete")
    public ResponseEntity<ResponseDTO> delete(@RequestParam String username){
        ResponseDTO responseDTO = userService.deleteUser(username);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
}
