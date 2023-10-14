package com.codegym.aurora.service;

import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.payload.response.VNPayResponseDTO;

import javax.servlet.http.HttpServletRequest;

public interface VNPayService {

    String createOrder(BuyVipRequestDTO buyVipRequestDTO, String baseUrl);

    VNPayResponseDTO oderReturn(HttpServletRequest httpServletRequest);


}
