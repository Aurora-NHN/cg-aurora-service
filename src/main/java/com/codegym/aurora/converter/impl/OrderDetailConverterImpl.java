package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.OrderDetailConverter;
import com.codegym.aurora.converter.ProductConverter;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.payload.request.OrderDetailRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDetailConverterImpl implements OrderDetailConverter {
    ProductConverter productConverter;

//    @Override
//    public OrderDetail converterOrderDetailRequestDTOToEntity(OrderDetailRequestDTO orderDetailRequestDTO) {
//        OrderDetail orderDetail = new OrderDetail();
//        List<Product> products = productConverter.convertProductListDTOToEntity(orderDetailRequestDTO.getProducts());
//        BeanUtils.copyProperties(orderDetailRequestDTO, orderDetail);
//        orderDetail.setProduct(products);
//        return orderDetail;
//    }

    @Override
    public List<OrderDetailRequestDTO> converterOrderDetailListRequestEntityToDTO(List<OrderDetail> orderDetailList) {
        List<OrderDetailRequestDTO> orderDetails = new ArrayList<>();
        for(OrderDetail orderDetail:orderDetailList){
            OrderDetailRequestDTO OrderDetailRequestDTO = new OrderDetailRequestDTO();
            BeanUtils.copyProperties(orderDetail,OrderDetailRequestDTO);
            orderDetails.add(OrderDetailRequestDTO);
        }
        return orderDetails;
    }
}
