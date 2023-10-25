package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createOrder(
            @RequestBody AddressRequestDTO addressRequestDTO
    ) {
        ResponseDTO responseDTO = orderService.createOrder(addressRequestDTO);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
}
