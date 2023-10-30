package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.request.ProductRequestInCartLineDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductImageResponseDTO;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.ProductImageRepository;
import com.codegym.aurora.repository.ProductRepository;
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
    private final ProductImageRepository productImageRepository;
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

    @Override
    public List<Product> convertProductListDTOToEntity(List<ProductRequestDTO> productRequestDTOS) {
        List<Product> products = new ArrayList<>();
        for (ProductRequestDTO productRequestDTO:productRequestDTOS){
            Product product = new Product();
            BeanUtils.copyProperties(productRequestDTO,product);
            products.add(product);
        }
        return products;
    }

    @Override
    public ProductResponseDTO convertProductEntityInCartLineToDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product,productResponseDTO);
        List<ProductImage> productImageList = productImageRepository.findProductImageByProductId(product.getId());
        List<ProductImageResponseDTO> productImageResponseDTOList = convertProductImageEntityToDTO(productImageList);
        productResponseDTO.setProductImageUrlList(productImageResponseDTOList);
        return productResponseDTO;
    }

    @Override
    public Product convertProductDTOToEntity(ProductRequestInCartLineDTO productRequestInCartLineDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequestInCartLineDTO,product);
        return product;
    }

    @Override
    public List<ProductResponseDTO> convertProductListEntityToDTO(List<Product> productList) {
        List<ProductResponseDTO> products = new ArrayList<>();
        for (Product product:productList){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            BeanUtils.copyProperties(product,productResponseDTO);
            products.add(productResponseDTO);
            List<ProductImage> productImageList = product.getProductImageUrlList();
            List<ProductImageResponseDTO> productImageResponseDTOList= convertProductImageEntityToDTO(productImageList);
            productResponseDTO.setProductImageUrlList(productImageResponseDTOList);
        }
        return products;
    }

    @Override
    public ProductInAdminResponseDTO convert(Product product) {
        ProductInAdminResponseDTO productResponseDTO = new ProductInAdminResponseDTO();
        BeanUtils.copyProperties(product,productResponseDTO);
        List<ProductImage> productImageList = productImageRepository.findProductImageByProductId(product.getId());
        List<ProductImageResponseDTO> productImageResponseDTOList = convertProductImageEntityToDTO(productImageList);
        productResponseDTO.setProductImageUrlList(productImageResponseDTOList);
        return productResponseDTO;
    }


    @Override
    public Page<ProductInAdminResponseDTO> convert(Page<Product> products) {
        List<ProductInAdminResponseDTO> productDtoList = new ArrayList<>();
        List<ProductImageResponseDTO> productImageResponseDTOList = new ArrayList<>();
        for (Product product : products.getContent()) {
            if (product.getProductImageUrlList() == null) {
                ProductInAdminResponseDTO productDto = convert(product);
                productDtoList.add(productDto);
            } else {
                ProductInAdminResponseDTO productDto = convert(product);
                productImageResponseDTOList = convertProductImageEntityToDTO(product.getProductImageUrlList());
                productDto.setProductImageUrlList(productImageResponseDTOList);
                productDtoList.add(productDto);
            }
        }
        return new PageImpl<>(productDtoList, products.getPageable(), products.getTotalElements());
    }

    @Override
    public List<ProductInAdminResponseDTO> convertProductsToReponseList(List<Product> productList) {
        List<ProductInAdminResponseDTO> products = new ArrayList<>();
        for (Product product:productList){
            ProductInAdminResponseDTO productResponseDTO = new ProductInAdminResponseDTO();
            BeanUtils.copyProperties(product,productResponseDTO);
            products.add(productResponseDTO);
            List<ProductImage> productImageList = product.getProductImageUrlList();
            List<ProductImageResponseDTO> productImageResponseDTOList= convertProductImageEntityToDTO(productImageList);
            productResponseDTO.setProductImageUrlList(productImageResponseDTOList);
        }
        return products;
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
