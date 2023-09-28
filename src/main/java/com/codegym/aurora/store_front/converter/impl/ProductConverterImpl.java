package com.codegym.aurora.store_front.converter.impl;

import com.codegym.aurora.store_front.converter.ProductConverter;
import com.codegym.aurora.store_front.entity.Product;
import com.codegym.aurora.store_front.payload.response.HomeProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverterImpl implements ProductConverter {

    @Override
    public HomeProductResponseDTO convertEntityToProductDTO(Product product) {
        return HomeProductResponseDTO.builder()
                .productName(product.getName())
                .id(product.getId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .quantitySold(product.getQuantitySold())
                .imageUrl(product.getImageUrl())
                .build();
    }

    @Override
    public Page<HomeProductResponseDTO> convertPageEntityToProductsDTO(Page<Product> productList) {
//        List<HomeProductResponseDTO> productDtoList = new ArrayList<>();
//
//        for (Product product : productList.getContent()) {
//            HomeProductResponseDTO productDto = convertEntityToProductDTO(product);
//            productDtoList.add(productDto);
//        }
//
//        return new PageImpl<>(productDtoList, productList.getPageable(), productList.getTotalElements());
        int pageNumber = 1;
        int pageSize = productList.getSize();

        List<Product> content = productList.getContent();
        List<Product> pageContent = content.subList(pageNumber * pageSize, Math.min((pageNumber + 1) * pageSize, content.size()));

        List<HomeProductResponseDTO> productDtoList = pageContent.stream()
                .map(this::convertEntityToProductDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(productDtoList, productList.getPageable(), productList.getTotalElements());
    }
}
