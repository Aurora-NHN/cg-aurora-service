package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.AddressConvert;
import com.codegym.aurora.converter.OrderConverter;
import com.codegym.aurora.converter.OrderDetailConverter;
import com.codegym.aurora.entity.Address;
import com.codegym.aurora.entity.Order;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.payload.response.AddressResponseDTO;
import com.codegym.aurora.payload.response.OrderDetailResponseDTO;
import com.codegym.aurora.payload.response.OrderResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter {
    private final OrderDetailConverter orderDetailConverter;
    private final AddressConvert addressConvert;
    @Override
    public OrderResponseDTO convertOrderEntityToDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        List<OrderDetail> orderDetailList=order.getOrderDetailList();
        List<OrderDetailResponseDTO> orderResponseDTOS = orderDetailConverter.converterOrderDetailListEntityToDTO(orderDetailList);
        Address address = order.getAddress();
        AddressResponseDTO addressResponseDTO = addressConvert.convertAddressRequestEntityToDTO(address);
        BeanUtils.copyProperties(order,orderResponseDTO);
        orderResponseDTO.setAddressResponseDTO(addressResponseDTO);
        orderResponseDTO.setOrderDetailList(orderResponseDTOS);
        return orderResponseDTO;
    }
}
