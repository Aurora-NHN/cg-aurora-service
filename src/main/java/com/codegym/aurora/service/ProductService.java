package com.codegym.aurora.service;


import com.codegym.aurora.payload.request.ProductCreateRequestDto;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Page<PageProductResponseDTO> getProductsPage(Pageable pageable);
    Page<PageProductResponseDTO> searchProductsByName(String keyWord, Pageable pageable,String sortOrder);

    Page<PageProductResponseDTO> findProductsBySubCategoryId(long id, Pageable pageable,String sortOrder);

    Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable);
    Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable);

    boolean addProduct(ProductRequestDTO productRequestDTO) throws IOException;
    List<ProductResponseDTO> getOtherProducts();


    Page<ProductInAdminResponseDTO> getProductsPageInAdmin(Pageable pageable);

    ResponseEntity<Object> deleteByProductId(Long id);

    ResponseEntity<Object> createProduct(ProductCreateRequestDto productRequestDTO);

    ResponseEntity<Object> updateProduct(ProductCreateRequestDto productRequestDTO);
}
