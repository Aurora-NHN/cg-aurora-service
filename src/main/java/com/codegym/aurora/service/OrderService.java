package com.codegym.aurora.service;


import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

import javax.transaction.Transactional;

public interface OrderService {
    ResponseDTO createOrderDetail(AddressRequestDTO addressRequestDTO);
    ResponseDTO createOrder();
}
