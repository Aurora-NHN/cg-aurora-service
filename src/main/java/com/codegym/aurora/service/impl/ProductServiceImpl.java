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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductConverter productConverter;

    private List<Integer> generateRandomPages(long totalItems, int pageSize) {
        List<Integer> allPageNumbers = new ArrayList<>();
        for (int i = 0; i < Math.ceil((double) totalItems / pageSize); i++) {
            allPageNumbers.add(i);
        }
        Collections.shuffle(allPageNumbers);
        return allPageNumbers;
    }

    private int getRandomPageNumber(List<Integer> pageNumbers) {
        Random random = new Random();
        return pageNumbers.get(random.nextInt(pageNumbers.size()));
    }

    @Override
    public Page<PageProductResponseDTO> getProductsPage(Pageable pageable) {
        long totalProducts = productRepository.count();
        List<Integer> randomPages = generateRandomPages(totalProducts, pageable.getPageSize());
        int randomPageNumber = getRandomPageNumber(randomPages);
        PageRequest randomPageRequest = PageRequest.of(randomPageNumber, pageable.getPageSize());
        Page<Product> randomProductPage = productRepository.findAll(randomPageRequest);
        Page<PageProductResponseDTO> randomProductPageDTO = productConverter.convertPageEntityToDtoPage(randomProductPage);

        return randomProductPageDTO;
    }

    @Override
    public Page<PageProductResponseDTO> searchProductsByName(String keyWord, Pageable pageable, String sortOrder) {
        Page<PageProductResponseDTO> pageSearchProductResponseDTOS = null;
        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").ascending());
                Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyWord, pageable);
                pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending());
                Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyWord, pageable);
                pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            }
        } else {
            Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyWord, pageable);
            pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        }
        return pageSearchProductResponseDTOS;

    }

    @Override
    public Page<PageProductResponseDTO> findProductsBySubCategoryId(long subCategoryId, Pageable pageable,String sortOrder) {
        Page<PageProductResponseDTO> pageProductResponseDTOS = null;
        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").ascending());
                Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
                pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending());
                Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
                pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            }
        } else {
            Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
            pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        }
        return pageProductResponseDTOS;
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
