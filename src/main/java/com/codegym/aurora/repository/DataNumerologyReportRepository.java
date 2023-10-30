package com.codegym.aurora.repository;

import com.codegym.aurora.entity.DataNumerologyReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataNumerologyReportRepository extends  JpaRepository<DataNumerologyReport, Long> {

    @Query("SELECT r FROM DataNumerologyReport r WHERE r.user.id = :userId")
    Page<DataNumerologyReport> findDataNumerologyReportByUserId(Long userId, Pageable pageable);
}
