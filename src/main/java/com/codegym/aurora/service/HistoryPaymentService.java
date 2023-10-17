package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.BillResponseDTO;

import java.util.UUID;

public interface HistoryPaymentService {

    BillResponseDTO getBill(UUID paymentId);
}
