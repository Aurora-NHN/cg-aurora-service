package com.codegym.aurora.payload.request;

import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLineRequestDTO {

    private long id;


    private int productCount;


    private long total;


    private OrderDetail orderDetail;

    private Product product;
}
