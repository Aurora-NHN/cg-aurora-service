package com.codegym.aurora.cache;

import com.codegym.aurora.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CartCache {
    private final Map<Long, Cart> cartMap;
    private CartCache(){
        cartMap = new HashMap<>();
    }

    public void addToCart(Long userId, Cart cart){
        cartMap.put(userId,cart);
    }
    public void updateCart(Long userId, Cart cart){cartMap.put(userId,cart);}
    public Cart getCart(Long userId){
        return cartMap.get(userId);
    }
}
