package com.codegym.aurora.converter;

import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.payload.response.CartDTO;

public interface CartConverter {
    CartDTO convertCartEntityToDTO(Cart cart);
}
