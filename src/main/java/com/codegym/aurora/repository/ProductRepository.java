package com.codegym.aurora.repository;


import com.codegym.aurora.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.isDelete = false AND p.isActivated = true ")
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isDelete = false AND p.isActivated = true AND LOWER(p.name) LIKE %:keyWord%")
    Page<Product> findByNameContainingIgnoreCase(@Param("keyWord") String keyWord, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isDelete = false AND p.isActivated = true AND p.subCategory.id = :id")
    Page<Product> findProductsBySubCategoryId(@Param("id") long id, Pageable pageable);

    Product findProductById(Long id);
    @Query("SELECT p FROM Product p WHERE p.isDelete = false AND p.isActivated = true AND LOWER(p.name) LIKE %:keyWord% OR LOWER(p.producer) LIKE %:keyWord%")
    Page<Product> findProductsByNameOrProducerContainingIgnoreCase(String keyWord, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM product ORDER BY RAND() LIMIT 4")
    List<Product> findRandomProducts(int limit);

    @Query("SELECT p FROM Product p WHERE p.isDelete = false")
    Page<Product> findAllProduct(Pageable pageable);
}