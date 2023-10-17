package com.codegym.aurora.converter;

import com.codegym.aurora.entity.CartLine;
import com.codegym.aurora.payload.response.CartLineDTO;

import java.util.List;

public interface CartLineConverter {
    List<CartLineDTO> convertCartLineListEntityToDTO(List<CartLine> cartLineList);
}
