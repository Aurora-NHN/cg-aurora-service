package com.codegym.aurora.service.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductDetailResponseDTO;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private  final ProductConverter productConverter;

    @Override
    public Page<HomeProductResponseDTO> getProductsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<HomeProductResponseDTO> productsDTOPage = productConverter.convertPageEntityToPageDTO(productPage);
        return productsDTOPage;
    }

    @Override
    public ProductDetailResponseDTO getProductDetail(long id) {
        Product product = productRepository.findProductById(id);
        ProductDetailResponseDTO productDetailResponseDTO = productConverter.convertEntityToProductDetailDTO(product);
        return productDetailResponseDTO;
    }

    @Override
    public Page<PageProductResponseDTO> searchProductsByName(String keyWord, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyWord,pageable);
        Page<PageProductResponseDTO> pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        return pageSearchProductResponseDTOS;
    }


    @Override
    public Page<PageProductResponseDTO> findProductsBySubCategoryId(long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findProductsBySubCategoryId(productId,pageable);
        Page<PageProductResponseDTO> pageProductBySubcategoryResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        return pageProductBySubcategoryResponseDTOS;
    }
}
