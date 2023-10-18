//package com.codegym.aurora.service.impl;
//
//import com.codegym.aurora.converter.OrderConverter;
//import com.codegym.aurora.entity.Address;
//import com.codegym.aurora.entity.Order;
//import com.codegym.aurora.entity.OrderDetail;
//import com.codegym.aurora.entity.Product;
//import com.codegym.aurora.entity.User;
//import com.codegym.aurora.payload.request.OrderRequestDTO;
//import com.codegym.aurora.repository.OrderDetailRepository;
//import com.codegym.aurora.repository.OrderRepository;
//import com.codegym.aurora.repository.UserRepository;
//import com.codegym.aurora.security.JwtTokenProvider;
//import com.codegym.aurora.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService {
//    JwtTokenProvider jwtTokenProvider;
//    UserRepository userRepository;
//    OrderConverter orderConverter;
//    OrderRepository orderRepository;
//    OrderDetailRepository orderDetailRepository;
//
//    @Override
//    public Order createOrder(OrderRequestDTO orderRequestDTO, String token) {
//        String currentUserName = jwtTokenProvider.getUsernameFromJWT(token);
//        User user = userRepository.findByUsername(currentUserName);
//        if (user == null) {
//            throw new RuntimeException("Token không hợp lệ");
//        }
//
//        Order order = orderConverter.convertOrderEntityToDTO(orderRequestDTO);
//        Address address = order.getAddress();
//        long totalAmount = 0;
//        List<OrderDetail> orderDetailList = order.getOrderDetailList();
//        for (OrderDetail orderDetail : orderDetailList) {
//            Product product = orderDetail.getProduct();
//            product.setQuantitySold(orderDetail.getQuantity());
//            int productQuantity = 0;
//            productQuantity -= orderDetail.getQuantity();
//            product.setQuantity(productQuantity);
//            totalAmount += orderDetail.getTotalPrice();
//            orderDetail.setOrder(order);
//        }
//        order.setTotalAmount(totalAmount);
//        order.setUser(user);
//        order.setStatus("Đang xử lí và đóng gói đơn hàng");
//        order.setOrderDate(new Date());
//
//        orderRepository.save(order);
//        orderDetailRepository.saveAll(orderDetailList);
//
//        return order;
//    }
//}
