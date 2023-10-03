package com.codegym.aurora.store_front.repository;

import com.codegym.aurora.store_front.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    Page<Product> findAll(Pageable pageable);
    Product findProductById(long id);
    Page<Product> findByNameContainingIgnoreCase(String keyWord, Pageable pageable);
    Page<Product> findProductsBySubCategoryId(long id,Pageable pageable);

}
