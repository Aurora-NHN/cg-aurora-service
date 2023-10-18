package com.codegym.aurora.service;

import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> saveListImage(List<String> stringList, Product product);
}
