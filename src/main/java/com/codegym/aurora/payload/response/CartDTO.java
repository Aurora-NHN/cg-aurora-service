package com.codegym.aurora.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class CartDTO {
    private List<CartLineDTO> cartLineDTOList;
    private Long totalAmount;
}
