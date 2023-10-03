package com.codegym.aurora.repository;


import com.codegym.aurora.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String keyWord, Pageable pageable);
    Page<Product> findProductsBySubCategoryId(long id,Pageable pageable);

}
