package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductConverterImpl implements ProductConverter {




    @Override
    public Page<PageProductResponseDTO> convertPageEntityToDtoPage(Page<Product> products) {
        List<PageProductResponseDTO> productDtoList = new ArrayList<>();
        List<ProductImageResponseDTO> productImageResponseDTOList = new ArrayList<>();
        for (Product product : products.getContent()) {
            if (product.getProductImageUrlList() == null) {
                PageProductResponseDTO productDto = convertProductEntityToDTO(product);
                productDtoList.add(productDto);
            } else {
                PageProductResponseDTO productDto = convertProductEntityToDTO(product);
                productImageResponseDTOList = convertProductImageEntityToDTO(product.getProductImageUrlList());
                productDto.setProductImageUrlList(productImageResponseDTOList);
                productDtoList.add(productDto);

            }

        }

        return new PageImpl<>(productDtoList, products.getPageable(), products.getTotalElements());
    }

    @Override
    public PageProductResponseDTO convertProductEntityToDTO(Product product) {
        PageProductResponseDTO homeProductResponseDTO = new PageProductResponseDTO();
        BeanUtils.copyProperties(product, homeProductResponseDTO);
        return homeProductResponseDTO;
    }

    public List<ProductImageResponseDTO> convertProductImageEntityToDTO(List<ProductImage> productImageList) {
        List<ProductImageResponseDTO> productImageResponseDTOList = new ArrayList<>();
        for (ProductImage productImage : productImageList) {
            ProductImageResponseDTO productImageResponseDTO = new ProductImageResponseDTO();
            BeanUtils.copyProperties(productImage, productImageResponseDTO);
            productImageResponseDTOList.add(productImageResponseDTO);
        }
        return productImageResponseDTOList;
    }

}
