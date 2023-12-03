package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.response.BillResponseDTO;
import com.codegym.aurora.repository.HistoryPaymentRepository;
import com.codegym.aurora.service.HistoryPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryPaymentServiceImpl implements HistoryPaymentService {

    private final HistoryPaymentRepository historyPaymentRepository;

    @Override
    public BillResponseDTO getBill(String paymentId) {
        BillResponseDTO bill = new BillResponseDTO();
        HistoryPayment historyPayment = historyPaymentRepository.findByPaymentId(paymentId);
        User user = historyPayment.getUser();
        UserDetail userDetail = user.getUserDetail();
        bill.setPaymentId(paymentId);
        bill.setPaymentTime(historyPayment.getPaymentTime());
        bill.setOrderInfo(historyPayment.getOderInfo());
        bill.setTotalPrice(historyPayment.getTotalPrice());
        bill.setFullName(userDetail.getFullName());
        return bill;
    }
}
