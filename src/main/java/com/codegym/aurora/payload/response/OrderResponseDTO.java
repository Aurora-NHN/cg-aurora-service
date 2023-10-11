package com.codegym.aurora.payload.response;

import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private long id;

    private Date orderDate;

    private long totalAmount;

    private String deliveryAddress;

    private String status;

    private List<OrderResponseDTO> orderDetailList = new ArrayList<>();

    private User user;
}
