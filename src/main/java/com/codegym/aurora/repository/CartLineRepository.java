package com.codegym.aurora.repository;

import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartLineRepository extends JpaRepository<CartLine, Long> {
    void deleteByCartId(Long cartId);
    void deleteCartLineByProductId(long productId);
}