package com.codegym.aurora.service.impl;

import com.codegym.aurora.cache.CartCache;
import com.codegym.aurora.cache.OrderCache;
import com.codegym.aurora.converter.AddressConvert;
import com.codegym.aurora.converter.OrderConverter;
import com.codegym.aurora.entity.Address;
import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.CartLine;
import com.codegym.aurora.entity.Order;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.OrderResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.CartRepository;
import com.codegym.aurora.repository.OrderRepository;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.OrderService;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AddressConvert addressConvert;
    private final CartCache cartCache;
    private final OrderCache orderCache;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderConverter orderConverter;

    @Override
    public ResponseDTO createOrderDetail(AddressRequestDTO addressRequestDTO) {
        Address address = addressConvert.convertAddressRequestDTOToEntity(addressRequestDTO);
        String username = userService.getCurrentUsername();
        User user = userRepository.findByUsername(username);
        Cart cartUser = cartCache.getCart(user.getId());
        Order order = new Order();
        if (address.getCity().equalsIgnoreCase("Hồ Chí Minh")) {
            address.setDeliveryCharges(25000);
        } else {
            address.setDeliveryCharges(35000);
        }
        order.setTotalAmount(cartUser.getTotalAmount() + address.getDeliveryCharges());
        address.setOrder(order);
        order.setAddress(address);
        orderCache.addToOrder(username, order);
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message("create cache order successfully")
                .build();
    }

    @Override
    @Transactional
    public ResponseDTO createOrder() {
        String username = userService.getCurrentUsername();
        User user = userRepository.findByUsername(username);
        Cart currentCart = cartRepository.findCartByUser(user);
        Order order = orderCache.getOrder(username);
        order.setOrderDate(new Date());
        order.setUser(user);
        List<CartLine> cartLineList = currentCart.getCartLineList();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (int id = 0; id < cartLineList.size(); id++) {
            CartLine cartLine = cartLineList.get(id);
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cartLine, orderDetail);
            orderDetailList.add(orderDetail);
            Product cartLineProduct = cartLine.getProduct();
            Product product = productRepository.findProductById(cartLineProduct.getId());
            int quantity = product.getQuantity();
            int cartLineQuantity = cartLine.getQuantity();
            int newQuantity = quantity - cartLineQuantity;
            product.setQuantity(newQuantity);
            productRepository.save(product);
        }
        order.setStatus("Đang chờ vận chuyển");
        order.setOrderDetailList(orderDetailList);
        orderRepository.save(order);
        OrderResponseDTO orderResponseDTO = orderConverter.convertOrderEntityToDTO(order);
        return ResponseDTO.builder()
                .data(orderResponseDTO)
                .status(HttpStatus.OK)
                .message("create order successfully")
                .build();
    }
}

