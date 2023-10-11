package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.Order;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.response.OrderResponseDTO;
import com.codegym.aurora.service.OrderService;
import com.codegym.aurora.service.UserService;

import java.util.Date;
import java.util.List;

//public class OrderServiceImpl   {
//    @Override
//    public OrderResponseDTO createOrder(List<CartLine> cartlineList, String deliveryAddress) {
//        if (user == null) {
//            throw new RuntimeException("Token không hợp lệ");
//        }
//
//        // Tạo đơn hàng.
//        Order order = new Order();
//        order.setUser(user);
//        order.setOrderDate(new Date());
//        order.setStatus("Đang xử lý");
//        order.setDeliveryAddress(deliveryAddress);
//
//        List<OrderDetail> orderDetails = cartLines.stream().map(cartLine -> {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrder(order);
//            orderDetail.setProduct(cartLine.getProductId());
//            orderDetail.setQuantity(cartLine.getQuantity());
//            orderDetail.setPrice(cartLine.getPrice());
//            return orderDetail;
//        }).collect(Collectors.toList());
//
//        orderRepository.save(order);
//        orderDetails.forEach(orderRepository::save);
//
//        return order;
//    }
//}
