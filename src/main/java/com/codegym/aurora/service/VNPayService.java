package com.codegym.aurora.service;

import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.payload.response.VNPayResponseDTO;

import java.util.Map;
import java.util.UUID;

public interface VNPayService {

    String createOrder(BuyVipRequestDTO buyVipRequestDTO);

    VNPayResponseDTO oderReturn(UUID paymentId);

    void setVip(String price, User user);

    HistoryPayment checkBill(Map<String, String[]> params);

}
