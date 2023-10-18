package com.codegym.aurora.repository;

import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByUser(User user);
}
