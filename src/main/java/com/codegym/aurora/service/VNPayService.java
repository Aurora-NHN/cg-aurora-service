package com.codegym.aurora.service;

import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.PaymentRequestDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface VNPayService {

    String createOrder(PaymentRequestDTO paymentRequestDTO, HttpServletRequest request);

    void setVip(String price, User user);

    HistoryPayment checkBill(Map<String, String[]> params);

}
