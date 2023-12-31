package com.codegym.aurora.repository;

import com.codegym.aurora.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    List<SubCategory> findSubCategoriesByCategoryId(long id);
    Page<SubCategory> findAllByIsDeleteIsFalse(Pageable pageable);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.active = true")
    List<SubCategory> findAllByActivatedTrue();

    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.active = true")
    SubCategory findSubCategoryByActivatedTrue(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.active = true and sc.isDelete = false ")
    SubCategory findSubCategoryByActivatedTrueAndDeletedFalse(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.active = false and sc.isDelete= false ")
    SubCategory findSubCategoryByActivatedFalseAndDeletedFalse(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.id = :id and sc.isDelete = false ")
    SubCategory findSubCategoryByIdAndDeletedFalse(Long id);
    @Query("SELECT sc FROM SubCategory sc WHERE sc.name = :name and sc.active = true ")
    SubCategory findSubCategoryByNameAndActivatedTrue(String name);

    List<SubCategory> findAllByActiveIsTrue();
}
