package com.codegym.aurora.service.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private  final ProductConverter productConverter;


    @Override
    public Page<PageProductResponseDTO> getProductsPage(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> productsDTOPage = productConverter.convertPageEntityToDtoPage(productPage);
        return productsDTOPage;
    }

    @Override
    public Page<PageProductResponseDTO> searchProductsByName(String keyWord, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyWord,pageable);
        Page<PageProductResponseDTO> pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        return pageSearchProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> findProductsBySubCategoryId(long subCategoryId, Pageable pageable) {
        Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId,pageable);
        Page<PageProductResponseDTO> pageProductBySubcategoryResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        return pageProductBySubcategoryResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").ascending());
        Page<Product> products = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(products);
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending());
        Page<Product> products = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(products);
        return pageProductResponseDTOS;
    }
}
