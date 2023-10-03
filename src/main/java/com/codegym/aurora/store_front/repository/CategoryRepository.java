package com.codegym.aurora.store_front.repository;

import com.codegym.aurora.store_front.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
