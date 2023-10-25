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

    private long ProductId;

    private int quantity;

}
