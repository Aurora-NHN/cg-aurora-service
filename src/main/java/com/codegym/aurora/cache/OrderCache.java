package com.codegym.aurora.cache;

import com.codegym.aurora.entity.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderCache {
    private final Map<String, Order> orderMap;

    private OrderCache() {
        orderMap = new HashMap<>();
    }

    public void addToOrder(String username, Order order) {
        orderMap.put(username, order);
    }

    public Order getOrder(String username) {
        return orderMap.get(username);
    }

}
