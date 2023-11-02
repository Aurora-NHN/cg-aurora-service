package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.ProductCreateRequestDto;
import com.codegym.aurora.payload.request.ProductRequestDTO;
import com.codegym.aurora.payload.request.ProductRequestInCartLineDTO;
import com.codegym.aurora.payload.response.PageProductResponseDTO;
import com.codegym.aurora.payload.response.ProductCreateResponseDTO;
import com.codegym.aurora.payload.response.ProductImageResponseDTO;
import com.codegym.aurora.payload.response.ProductInAdminResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;
import com.codegym.aurora.repository.ProductImageRepository;
import com.codegym.aurora.repository.SubCategoryRepository;
import com.codegym.aurora.service.ImageService;
import com.codegym.aurora.service.ProductImageService;
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
    private final SubCategoryRepository subCategoryRepository;
    private final ImageService imageService;
    private final SubCategoryConverter subCategoryConverter;
    private final ProductImageService productImageService;
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
    public Page<ProductInAdminResponseDTO> convert(Page<Product> productPage) {
        List<ProductInAdminResponseDTO> productResponseDtos = new ArrayList<>();
        List<String> productImageUrls;
        String mainProductImageUrl;
        for (Product product : productPage.getContent()){
            ProductInAdminResponseDTO productResponseDTO = new ProductInAdminResponseDTO();
            BeanUtils.copyProperties(product,productResponseDTO);
            List<ProductImage> productImageList = product.getProductImageUrlList();
            String mainProductImageName = product.getImageName();
            if (!productImageList.isEmpty()){
                productImageUrls = productImageService.getProductUrls(productImageList);
                productResponseDTO.setProductImageUrlList(productImageUrls);
            }
            if (mainProductImageName != null){
                mainProductImageUrl = imageService.getImageUrl(mainProductImageName);
                productResponseDTO.setImageUrl(mainProductImageUrl);
            }else {
                productResponseDTO.setImageUrl(product.getImageUrl());
            }
            SubCategoryResponseDTO subCategoryResponseDTO = subCategoryConverter
                    .convertEntityToSubCategoryResponeDto(product.getSubCategory());
            productResponseDTO.setSubCategory(subCategoryResponseDTO);
            productResponseDtos.add(productResponseDTO);
        }
        return new PageImpl<>(productResponseDtos, productPage.getPageable(), productPage.getTotalElements());
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
    public List<ProductInAdminResponseDTO> convertProductsToReponseList(List<Product> productList) {
        List<ProductInAdminResponseDTO> products = new ArrayList<>();
        List<String> productImageUrls = new ArrayList<>();
        for (Product product:productList){
            ProductInAdminResponseDTO productResponseDTO = new ProductInAdminResponseDTO();
            BeanUtils.copyProperties(product,productResponseDTO);
            List<ProductImage> productImageList = product.getProductImageUrlList();
            if (!productImageList.isEmpty()){
                productImageUrls = productImageService.getProductUrls(productImageList);
                productResponseDTO.setProductImageUrlList(productImageUrls);
            }
            products.add(productResponseDTO);
        }
        return products;
    }



    @Override
    public ProductCreateResponseDTO convertEntityToCreateResponse(Product product) {
        ProductCreateResponseDTO productResponseDTO = new ProductCreateResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);
        SubCategoryResponseDTO subCategory = subCategoryConverter
                .convertEntityToSubCategoryResponeDto(product.getSubCategory());
        productResponseDTO.setSubCategoryResponseDTO(subCategory);
        return productResponseDTO;
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
