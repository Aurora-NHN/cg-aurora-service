package com.codegym.aurora.store_front.converter;

import com.codegym.aurora.store_front.entity.Product;
import com.codegym.aurora.store_front.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.store_front.payload.response.ProductDetailResponseDTO;
import com.codegym.aurora.store_front.payload.response.PageProductResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductConverter {
   HomeProductResponseDTO convertEntityToProductHomeDTO(Product product);
   ProductDetailResponseDTO convertEntityToProductDetailDTO(Product product);
   Page<HomeProductResponseDTO> convertPageEntityToPageDTO(Page<Product> products);
   Page<PageProductResponseDTO> convertPageEntityToDtoPage(Page<Product> products);
   PageProductResponseDTO convertProductEntityToDTO(Product product);
}
