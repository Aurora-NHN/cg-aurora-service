package com.codegym.aurora.repository;

import com.codegym.aurora.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    List<ProductImage> findProductImageByProductId(long productId);
}
