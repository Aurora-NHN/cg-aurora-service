package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.OrderDetailConverter;
import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.payload.response.OrderDetailResponseDTO;
import com.codegym.aurora.payload.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class OrderDetailConverterImpl implements OrderDetailConverter {
    private final ProductConverter productConverter;
    @Override
    public List<OrderDetailResponseDTO> converterOrderDetailListEntityToDTO(
            List<OrderDetail> orderDetailList) {
        List<OrderDetailResponseDTO> orderDetailResponseDTOList = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetailList) {
            OrderDetailResponseDTO orderDetailResponseDTO = new OrderDetailResponseDTO();
            BeanUtils.copyProperties(orderDetail, orderDetailResponseDTO);
            Product product = orderDetail.getProduct();
            ProductResponseDTO productResponseDTO = productConverter.convertProductEntityInCartLineToDTO(product);
            orderDetailResponseDTO.setProductResponseDTO(productResponseDTO);
            orderDetailResponseDTOList.add(orderDetailResponseDTO);
        }
        return orderDetailResponseDTOList;
    }
}
