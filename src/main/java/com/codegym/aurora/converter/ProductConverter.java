package com.codegym.aurora.converter;


import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductConverter {


    Page<PageProductResponseDTO> convertPageEntityToDtoPage(Page<Product> products);

    PageProductResponseDTO convertProductEntityToDTO(Product product);
}
