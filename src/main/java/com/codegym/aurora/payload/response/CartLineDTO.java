package com.codegym.aurora.payload.response;

import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Data
@RequiredArgsConstructor
public class CartLineDTO {
    private long id;

    private int quantity;

    private long totalPrice;

    private ProductResponseDTO productResponseDTO;

}
