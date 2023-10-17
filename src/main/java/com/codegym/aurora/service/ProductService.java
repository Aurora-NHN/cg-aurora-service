package com.codegym.aurora.service;



import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ProductService {
    Page<PageProductResponseDTO> getProductsPage(Pageable pageable);
    Page<PageProductResponseDTO> searchProductsByName(String keyWord, Pageable pageable,String sortOrder);

    Page<PageProductResponseDTO> findProductsBySubCategoryId(long id, Pageable pageable,String sortOrder);

    Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable);
    Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable);

    Product addProduct(ProductRequestDTO productRequestDTO) throws IOException;

}
