package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.CartConverter;
import com.codegym.aurora.converter.CartLineConverter;
import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.payload.response.CartDTO;
import com.codegym.aurora.payload.response.CartLineDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartConverterImpl implements CartConverter {
    private final CartLineConverter cartLineConverter;
    @Override
    public CartDTO convertCartEntityToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cart,cartDTO);
        List<CartLineDTO> cartLineDTOList = cartLineConverter.convertCartLineListEntityToDTO(cart.getCartLineList());
        cartDTO.setCartLineDTOList(cartLineDTOList);
        return cartDTO;
    }
}
