package com.codegym.aurora.repository;

import com.codegym.aurora.entity.BlogCategory;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {
}
