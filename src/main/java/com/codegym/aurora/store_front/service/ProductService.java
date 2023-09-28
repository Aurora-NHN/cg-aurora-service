package com.codegym.aurora.store_front.service;


import com.codegym.aurora.store_front.payload.response.HomeProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<HomeProductResponseDTO> getProductsPage(int page, int size);

}
