package com.codegym.aurora.service;



import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<PageProductResponseDTO> getProductsPage(Pageable pageable);
    Page<PageProductResponseDTO> searchProductsByName(String keyWord, int page, int size);

    Page<PageProductResponseDTO> findProductsBySubCategoryId(long id, Pageable pageable);

    Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable);
    Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable);

}
