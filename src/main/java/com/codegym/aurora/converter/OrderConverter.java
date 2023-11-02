package com.codegym.aurora.converter;

import com.codegym.aurora.entity.Order;
import com.codegym.aurora.payload.response.OrderResponseDTO;

public interface OrderConverter {
    OrderResponseDTO convertOrderEntityToDTO(Order order);
}
