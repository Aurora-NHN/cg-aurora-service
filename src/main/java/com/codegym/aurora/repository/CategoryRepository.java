package com.codegym.aurora.repository;


import com.codegym.aurora.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();
    @Query("SELECT c FROM Category c WHERE c.isActivated = true")
    Optional<List<Category>> findAllByActivatedTrue();

    @Query("SELECT c FROM Category c WHERE c.id = :id and c.isActivated = true")
    Category findCategoryByActivatedTrue(Long id);
    @Query("SELECT c FROM Category c WHERE c.id = :id and c.isActivated = true and c.isDelete = false ")
    Category findCategoryByActivatedTrueAndDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.id = :id and c.isActivated = false and c.isDelete = false ")
    Category findCategoryByActivatedFalseAndDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.id = :id and c.isDelete = false ")
    Category findCategoryByIdAndDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.name = :name and c.isDelete = false ")
    Category findCategoryByName(String name);
}
