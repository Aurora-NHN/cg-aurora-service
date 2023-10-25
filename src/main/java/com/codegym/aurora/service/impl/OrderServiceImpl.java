package com.codegym.aurora.service.impl;

import com.codegym.aurora.cache.CartCache;
import com.codegym.aurora.converter.AddressConvert;
import com.codegym.aurora.converter.CartConverter;
import com.codegym.aurora.entity.Address;
import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.CartLine;
import com.codegym.aurora.entity.Order;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.CartDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.CartLineRepository;
import com.codegym.aurora.repository.CartRepository;
import com.codegym.aurora.repository.OrderDetailRepository;
import com.codegym.aurora.repository.OrderRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.security.JwtTokenProvider;
import com.codegym.aurora.service.OrderService;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AddressConvert addressConvert;
    private final CartLineRepository cartLineRepository;
    private final CartCache cartCache;
    @Override
    public ResponseDTO createOrder(AddressRequestDTO addressRequestDTO) {
        Address address = addressConvert.convertAddressRequestDTOToEntity(addressRequestDTO);
        String username = userService.getCurrentUsername();
        User user = userRepository.findByUsername(username);
        Cart cartUser = cartCache.getCart(user.getId());
        Cart cart = cartRepository.findCartByUser(user);
        cart.setTotalAmount(cartUser.getTotalAmount());
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setTotalAmount(cart.getTotalAmount());
        order.setUser(user);
        order.setAddress(address);
        order.setStatus("Đang chuẩn bị hàng");
        if (address.getCity().equalsIgnoreCase("Hồ Chí Minh")) {
            address.setDeliveryCharges(25000);
        } else {
            address.setDeliveryCharges(35000);
        }
        order.setTotalAmount(cart.getTotalAmount() + address.getDeliveryCharges());
        List<CartLine> cartLineList = cart.getCartLineList();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartLine cartLine : cartLineList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cartLine, orderDetail);
            orderDetailList.add(orderDetail);
        }
        order.setOrderDetailList(orderDetailList);
        cartLineRepository.deleteAllByCartId(cart.getId());
        cart.setTotalAmount(0);
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message("create order successfully")
                .build();
    }
}
