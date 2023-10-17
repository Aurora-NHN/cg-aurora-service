package com.codegym.aurora.repository;


import com.codegym.aurora.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<ProductCategory,Long> {
    List<ProductCategory> findAll();
    @Query("SELECT c FROM ProductCategory c WHERE c.isActivated = true")
    Optional<List<ProductCategory>> findAllByActivatedTrue();

    @Query("SELECT c FROM ProductCategory c WHERE c.id = :id and c.isActivated = true")
    ProductCategory findCategoryByActivatedTrue(Long id);
    @Query("SELECT c FROM ProductCategory c WHERE c.id = :id and c.isActivated = true and c.isDelete = false ")
    ProductCategory findCategoryByActivatedTrueAndDeletedFalse(Long id);
    @Query("SELECT c FROM ProductCategory c WHERE c.id = :id and c.isActivated = false and c.isDelete = false ")
    ProductCategory findCategoryByActivatedFalseAndDeletedFalse(Long id);
    @Query("SELECT c FROM ProductCategory c WHERE c.id = :id and c.isDelete = false ")
    ProductCategory findCategoryByIdAndDeletedFalse(Long id);
    @Query("SELECT c FROM ProductCategory c WHERE c.name = :name and c.isDelete = false ")
    ProductCategory findCategoryByName(String name);
}
