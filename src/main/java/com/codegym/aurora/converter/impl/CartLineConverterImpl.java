package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.CartLineConverter;
import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.CartLine;
import com.codegym.aurora.payload.response.CartLineDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class CartLineConverterImpl implements CartLineConverter {
    private final ProductConverter productConverter;
    @Override
    public List<CartLineDTO> convertCartLineListEntityToDTO(List<CartLine> cartLineList) {
        List<CartLineDTO> cartLineDTOList = new ArrayList<>();
        for(CartLine cartLine:cartLineList){
            CartLineDTO cartLineDTO = new CartLineDTO();
            cartLineDTO.setQuantity(cartLine.getQuantity());
            cartLineDTO.setTotalPrice(cartLine.getTotalPrice());
            cartLineDTO.setId(cartLine.getId());
            ProductResponseDTO productResponseDTO = productConverter.convertProductEntityInCartLineToDTO(cartLine.getProduct());
            cartLineDTO.setProductResponseDTO(productResponseDTO);
            cartLineDTOList.add(cartLineDTO);
        }
        return cartLineDTOList;
    }
}
