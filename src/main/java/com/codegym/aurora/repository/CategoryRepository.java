package com.codegym.aurora.repository;


import com.codegym.aurora.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByIsDeleteIsFalse();
    List<Category> findAllByIsDeleteIsFalseAndActiveIsTrue();
    @Query("SELECT c FROM Category c WHERE c.active = true")
    Optional<List<Category>> findAllByActiveTrue();

    @Query("SELECT c FROM Category c WHERE c.id = :id and c.active = true")
    Category findCategoryByActiveTrue(Long id);
    @Query("SELECT c FROM Category c WHERE c.id = :id and c.active = true and c.isDelete = false ")
    Category findCategoryByActiveTrueAndDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.id = :id and c.active = false and c.isDelete = false ")
    Category findCategoryByActiveFalseAndDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.id = :id and c.isDelete = false ")
    Category findCategoryByIdAndDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.name = :name and c.isDelete = false ")
    Category findCategoryByName(String name);
}
