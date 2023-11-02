package com.codegym.aurora.converter;

import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.payload.response.OrderDetailResponseDTO;

import java.util.List;

public interface OrderDetailConverter {
    List<OrderDetailResponseDTO> converterOrderDetailListEntityToDTO(List<OrderDetail> orderDetailList);
}