package com.codegym.aurora.repository;

import com.codegym.aurora.entity.HistoryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryPaymentRepository extends JpaRepository<HistoryPayment, Long> {
}
