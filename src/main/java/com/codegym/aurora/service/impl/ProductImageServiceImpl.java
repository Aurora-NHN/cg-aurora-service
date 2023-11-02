package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.repository.ProductImageRepository;
import com.codegym.aurora.service.ImageService;
import com.codegym.aurora.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ImageService imageService;
    @Override
    public List<ProductImage> saveListImage(List<String> stringList, Product product) {
        List<ProductImage> productImageList = new ArrayList<>();
        for (String url:stringList){
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImageUrl(url);
            productImage.setImageName(url);
            productImageList.add(productImage);
            productImageRepository.save(productImage);
        }
        return productImageList;
    }

    @Override
    public List<String> getProductUrls(List<ProductImage> productImageList) {
        List<String> productNameImages = new ArrayList<>();
        for (ProductImage item: productImageList){
            if(item.getImageName() == null){
                productNameImages.add(item.getImageUrl());
            }else {
                String productName = imageService.getImageUrl(item.getImageName());
                productNameImages.add(productName);
            }
        }
        return productNameImages;
    }
}
