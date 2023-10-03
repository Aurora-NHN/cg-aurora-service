package com.codegym.aurora.store_front.service;


import com.codegym.aurora.store_front.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.store_front.payload.response.ProductDetailResponseDTO;
import com.codegym.aurora.store_front.payload.response.PageProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<HomeProductResponseDTO> getProductsPage(int page, int size);
    ProductDetailResponseDTO getProductDetail(long id);
    Page<PageProductResponseDTO> searchProductsByName(String keyWord, int page, int size);

    Page<PageProductResponseDTO> findProductsBySubCategoryId(long id,int page, int size);
}
