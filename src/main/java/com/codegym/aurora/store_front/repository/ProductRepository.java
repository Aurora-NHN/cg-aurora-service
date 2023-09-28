package com.codegym.aurora.store_front.repository;

import com.codegym.aurora.store_front.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
