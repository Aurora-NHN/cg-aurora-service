package com.codegym.aurora.service;




import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

public interface OrderService {
    ResponseDTO createOrder(AddressRequestDTO addressRequestDTO) ;
    }
