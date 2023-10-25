package com.codegym.aurora.service;


import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ProductService {
    Page<PageProductResponseDTO> getProductsPage(Pageable pageable);
    Page<PageProductResponseDTO> searchProductsByName(String keyWord, Pageable pageable,String sortOrder);

    Page<PageProductResponseDTO> findProductsBySubCategoryId(long id, Pageable pageable,String sortOrder);

    Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable);
    Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable);

    boolean addProduct(ProductRequestDTO productRequestDTO) throws IOException;

}
