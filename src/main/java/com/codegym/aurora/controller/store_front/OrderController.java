//package com.codegym.aurora.controller.store_front;
//
//import com.codegym.aurora.payload.request.OrderRequestDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/order")
//public class OrderController {
//    private final OrderService orderService;
//    public ResponseEntity<?> createOrder(
//            @RequestBody OrderRequestDTO orderRequestDTO,
//            @RequestHeader("Authorization") String token
//    ){
//        Order orderDTO = orderService.createOrder(orderRequestDTO,token);
//        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
//    }
//}
