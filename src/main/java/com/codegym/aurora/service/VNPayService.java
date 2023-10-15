package com.codegym.aurora.service;

import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.payload.request.VNPayRequestDTO;
import com.codegym.aurora.payload.response.VNPayResponseDTO;

import java.util.Map;

public interface VNPayService {

    String createOrder(BuyVipRequestDTO buyVipRequestDTO);

    VNPayResponseDTO oderReturn(VNPayRequestDTO vnPayRequestDTO);

    void setVip(String price, User user);

    void checkBill(Map<String, String[]> params);
}
