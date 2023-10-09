package com.codegym.aurora.repository;

import com.codegym.aurora.entity.LifePathNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifePathNumberRepository extends JpaRepository<LifePathNumber, Integer> {
    LifePathNumber findLifePathNumberByIndicators(int calculatorLifePathNumber);
}
