package com.codegym.aurora.repository;

import com.codegym.aurora.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    List<SubCategory> findSubCategoriesByCategoryId(long id);

}
