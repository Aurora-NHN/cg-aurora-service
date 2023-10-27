package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order-detail")
    public ResponseEntity<ResponseDTO> createOrderDetail(
            @RequestBody AddressRequestDTO addressRequestDTO
    ) {
        ResponseDTO responseDTO = orderService.createOrderDetail(addressRequestDTO);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveOrder(){
        ResponseDTO responseDTO = orderService.createOrder();
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
}
