package com.codegym.aurora.store_front.service.impl;

import com.codegym.aurora.store_front.converter.ProductConverter;
import com.codegym.aurora.store_front.entity.Product;
import com.codegym.aurora.store_front.payload.response.HomeProductResponseDTO;
import com.codegym.aurora.store_front.repository.ProductRepository;
import com.codegym.aurora.store_front.service.ProductService;
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

    private final ProductConverter productConverter;

    @Override
    public Page<HomeProductResponseDTO> getProductsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<HomeProductResponseDTO> PageProductDto = productConverter.convertPageEntityToProductsDTO(productPage);
        return PageProductDto;
    }
}
