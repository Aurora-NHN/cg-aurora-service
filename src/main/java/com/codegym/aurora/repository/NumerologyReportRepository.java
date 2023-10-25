package com.codegym.aurora.repository;

import com.codegym.aurora.entity.NumerologyReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumerologyReportRepository extends JpaRepository<NumerologyReport, Long> {

    List<NumerologyReport> findAllByUserId(Long id);
}
