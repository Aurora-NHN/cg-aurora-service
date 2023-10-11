package com.codegym.aurora.converter;

import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.payload.request.OrderDetailRequestDTO;

import java.util.List;

public interface OrderDetailConverter {
//    OrderDetail converterOrderDetailRequestDTOToEntity(OrderDetailRequestDTO orderDetailRequestDTO);
    List<OrderDetailRequestDTO> converterOrderDetailListRequestEntityToDTO(List<OrderDetail> orderDetailList);
}
