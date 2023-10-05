package com.codegym.aurora.service;


import com.codegym.aurora.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductDetailResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<HomeProductResponseDTO> getProductsPage(Pageable pageable);

    ProductDetailResponseDTO getProductDetail(long id);

    Page<PageProductResponseDTO> searchProductsByName(String keyWord, int page, int size);

    Page<PageProductResponseDTO> findProductsBySubCategoryId(long id, int page, int size);
}
