package com.codegym.aurora.repository;

import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryPaymentRepository extends JpaRepository<HistoryPayment, Long> {
    HistoryPayment findByUser(User user);
}
