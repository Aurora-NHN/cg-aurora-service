package com.codegym.aurora.store_front.converter;

import com.codegym.aurora.store_front.entity.Product;
import com.codegym.aurora.store_front.payload.response.HomeProductResponseDTO;
import org.springframework.data.domain.Page;


public interface ProductConverter {
   HomeProductResponseDTO convertEntityToProductDTO(Product product);
   Page<HomeProductResponseDTO> convertPageEntityToProductsDTO(Page<Product> productList);
}
