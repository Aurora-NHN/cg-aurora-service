package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.repository.ProductImageRepository;
import com.codegym.aurora.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    @Override
    public List<ProductImage> saveListImage(List<String> stringList, Product product) {
        List<ProductImage> productImageList = new ArrayList<>();
        for (String url:stringList){
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImageUrl(url);
            productImageList.add(productImage);
            productImageRepository.save(productImage);
        }
        return productImageList;
    }
}
