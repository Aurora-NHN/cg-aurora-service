package com.codegym.aurora.service.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.service.ImageService;
import com.codegym.aurora.service.ProductImageService;
import com.codegym.aurora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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

    private final ImageService imageService;

    private final ProductImageService productImageService;

    @Override
    public Page<PageProductResponseDTO> getProductsPage(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),9,Sort.by("quantitySold").descending());
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> searchProductsByName(String keyWord, Pageable pageable, String sortOrder) {
        Page<PageProductResponseDTO> pageSearchProductResponseDTOS = null;
        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").ascending());
                Page<Product> productPage = productRepository.findProductsByNameOrProducerContainingIgnoreCase(keyWord, pageable);
                pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").descending());
                Page<Product> productPage = productRepository.findProductsByNameOrProducerContainingIgnoreCase(keyWord, pageable);
                pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            }
        } else {
            pageable = PageRequest.of(pageable.getPageNumber(),9,Sort.by("quantitySold").descending());
            Page<Product> productPage = productRepository.findProductsByNameOrProducerContainingIgnoreCase(keyWord, pageable);
            pageSearchProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        }
        return pageSearchProductResponseDTOS;

    }

    @Override
    public Page<PageProductResponseDTO> findProductsBySubCategoryId(long subCategoryId, Pageable pageable,String sortOrder) {
        Page<PageProductResponseDTO> pageProductResponseDTOS = null;
        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").ascending());
                Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
                pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").descending());
                Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
                pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
            }
        } else {
            pageable = PageRequest.of(pageable.getPageNumber(),9,Sort.by("quantitySold").descending());
            Page<Product> productPage = productRepository.findProductsBySubCategoryId(subCategoryId, pageable);
            pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(productPage);
        }
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> getProductsDTOSortedAscending(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").ascending());
        Page<Product> products = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(products);
        return pageProductResponseDTOS;
    }

    @Override
    public Page<PageProductResponseDTO> getProductsDTOSortedDescending(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 9, Sort.by("price").descending());
        Page<Product> products = productRepository.findAll(pageable);
        Page<PageProductResponseDTO> pageProductResponseDTOS = productConverter.convertPageEntityToDtoPage(products);
        return pageProductResponseDTOS;
    }

    @Override
    public boolean addProduct(ProductRequestDTO productRequestDTO) throws IOException {
        try {
            String productImageUlr = imageService.save(productRequestDTO.getImage());
            List<String> productUrlList = imageService.save(productRequestDTO.getProductImageList());
            Product product = new Product();
            List<ProductImage> productImageList = productImageService.saveListImage(productUrlList,product);
            product.setImageUrl(productImageUlr);
            product.setName(productRequestDTO.getName());
            product.setDescription(productRequestDTO.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setQuantity(productRequestDTO.getQuantity());
            product.setWeight(productRequestDTO.getWeight());
            product.setProductImageUrlList(productImageList);
            product.setAuthor(productRequestDTO.getAuthor());
            product.setInclude(productRequestDTO.getInclude());
            product.setProducer(productRequestDTO.getProducer());
            productRepository.save(product);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    @Override
    public List<ProductResponseDTO> getOtherProducts() {
        List<Product> randomProducts = productRepository.findRandomProducts(4);
        List<ProductResponseDTO> productResponseDTOS = productConverter.convertProductListEntityToDTO(randomProducts);
        return productResponseDTOS;
    }
}