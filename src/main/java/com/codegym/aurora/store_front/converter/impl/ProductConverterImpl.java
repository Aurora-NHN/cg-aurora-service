package com.codegym.aurora.store_front.converter.impl;

import com.codegym.aurora.store_front.converter.ProductConverter;
import com.codegym.aurora.store_front.entity.Category;
import com.codegym.aurora.store_front.entity.Product;
import com.codegym.aurora.store_front.entity.ProductImage;
import com.codegym.aurora.store_front.payload.response.*;
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
    public HomeProductResponseDTO convertEntityToProductHomeDTO(Product product) {
        HomeProductResponseDTO homeProductResponseDTO = new HomeProductResponseDTO();
        BeanUtils.copyProperties(product, homeProductResponseDTO);
        return homeProductResponseDTO;
    }

    @Override
    public ProductDetailResponseDTO convertEntityToProductDetailDTO(Product product) {
        ProductDetailResponseDTO productDetailResponseDTO = new ProductDetailResponseDTO();
        List<ProductImageResponseDTO> productImageResponseDTOList= new ArrayList<>();
        if(product.getProductImageUrlList() == null){
            BeanUtils.copyProperties(product, productDetailResponseDTO);
        }else {
            BeanUtils.copyProperties(product, productDetailResponseDTO);
            productImageResponseDTOList = convertProductImageEntityToDTO(product.getProductImageUrlList());
            productDetailResponseDTO.setProductImageUrlList(productImageResponseDTOList);
        }
        return productDetailResponseDTO;
    }

    @Override
    public Page<HomeProductResponseDTO> convertPageEntityToPageDTO(Page<Product> products) {
        List<HomeProductResponseDTO> productDtoList = new ArrayList<>();

        for (Product product : products.getContent()) {
            HomeProductResponseDTO productDto = convertEntityToProductHomeDTO(product);
            productDtoList.add(productDto);
        }

        return new PageImpl<>(productDtoList, products.getPageable(), products.getTotalElements());
    }

    @Override
    public Page<PageProductResponseDTO> convertPageEntityToDtoPage(Page<Product> products) {
        List<PageProductResponseDTO> productDtoList = new ArrayList<>();

        for (Product product : products.getContent()) {
            PageProductResponseDTO productDto = convertProductEntityToDTO(product);
            productDtoList.add(productDto);
        }

        return new PageImpl<>(productDtoList, products.getPageable(), products.getTotalElements());
    }

    @Override
    public PageProductResponseDTO convertProductEntityToDTO(Product product) {
        PageProductResponseDTO homeProductResponseDTO = new PageProductResponseDTO();
        BeanUtils.copyProperties(product, homeProductResponseDTO);
        return homeProductResponseDTO;
    }

    public List<ProductImageResponseDTO> convertProductImageEntityToDTO(List<ProductImage> productImageList){
        List<ProductImageResponseDTO> productImageResponseDTOList = new ArrayList<>();
        for(ProductImage productImage:productImageList) {
            ProductImageResponseDTO productImageResponseDTO = new ProductImageResponseDTO();
            BeanUtils.copyProperties(productImage, productImageResponseDTO);
            productImageResponseDTOList.add(productImageResponseDTO);
        }
        return productImageResponseDTOList;
    }


}
