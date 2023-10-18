//package com.codegym.aurora.converter.impl;
//
//import com.codegym.aurora.converter.AddressConvert;
//import com.codegym.aurora.converter.OrderConverter;
//import com.codegym.aurora.converter.OrderDetailConverter;
//import com.codegym.aurora.entity.Address;
//import com.codegym.aurora.entity.Order;
//import com.codegym.aurora.entity.OrderDetail;
//import com.codegym.aurora.payload.request.OrderRequestDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class OrderConvertImpl implements OrderConverter {
//    OrderDetailConverter orderDetailConverter;
//    AddressConvert addressConvert;
//
//    @Override
//    public Order convertOrderEntityToDTO(OrderRequestDTO orderRequestDTO) {
//        Order order = new Order();
//        List<OrderDetail> orderDetailList = orderDetailConverter.converterOrderDetailListRequestDTOToEntity(orderRequestDTO.getOrderDetailRequestDTOList());
//        Address address = addressConvert.convertAddressRequestDTOToEntity(orderRequestDTO.getAddressRequestDTO());
//        order.setOrderDetailList(orderDetailList);
//        order.setAddress(address);
//        return order;
//    }
//}
