package com.codegym.aurora.payload.response;

import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private long id;

    private Date orderDate;

    private long totalAmount;

    private String deliveryAddress;

    private String status;

    private List<OrderDetailResponseDTO> orderDetailList = new ArrayList<>();

    private AddressResponseDTO addressResponseDTO;
}
