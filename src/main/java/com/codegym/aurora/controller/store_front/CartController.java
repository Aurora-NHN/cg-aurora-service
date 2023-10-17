package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addToCart(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "quantity") int quantity) {
        ResponseDTO responseDTO = cartService.addToCart(productId,quantity);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

    @GetMapping("/show-cart")
    public ResponseEntity<ResponseDTO> addToCart() {
        ResponseDTO responseDTO = cartService.showCart();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete-cart-line")
    public ResponseEntity<ResponseDTO> removeCartLine(
            @RequestParam(name = "cartLineId") long cartLineId){
        ResponseDTO responseDTO = cartService.removeCartLine(cartLineId);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
}
