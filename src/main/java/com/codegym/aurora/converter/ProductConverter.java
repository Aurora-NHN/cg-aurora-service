package com.codegym.aurora.converter;


import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductDetailResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductConverter {
   HomeProductResponseDTO convertEntityToProductHomeDTO(Product product);
   ProductDetailResponseDTO convertEntityToProductDetailDTO(Product product);
   Page<HomeProductResponseDTO> convertPageEntityToPageDTO(Page<Product> products);
   Page<PageProductResponseDTO> convertPageEntityToDtoPage(Page<Product> products);
   PageProductResponseDTO convertProductEntityToDTO(Product product);
}
