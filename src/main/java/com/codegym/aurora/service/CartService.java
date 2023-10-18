package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.ResponseDTO;

public interface CartService {
    ResponseDTO addToCart(Long productId,int quantity);
    ResponseDTO showCart();

    ResponseDTO removeCartLine(long cartLineId);
}
