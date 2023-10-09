package com.codegym.aurora.repository;

import com.codegym.aurora.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    List<SubCategory> findSubCategoriesByCategoryId(long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.isActivated = true")
    List<SubCategory> findAllByActivatedTrue();

    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.isActivated = true")
    SubCategory findSubCategoryByActivatedTrue(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.isActivated = true and sc.isDelete = false ")
    SubCategory findSubCategoryByActivatedTrueAndDeletedFalse(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.isActivated = false and sc.isDelete= false ")
    SubCategory findSubCategoryByActivatedFalseAndDeletedFalse(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.isDelete = false ")
    SubCategory findSubCategoryByIdAndDeletedFalse(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.name = :name and sc.isActivated = true ")
    SubCategory findSubCategoryByNameAndActivatedTrue(String name);
}
