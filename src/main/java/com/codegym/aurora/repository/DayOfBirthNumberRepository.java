package com.codegym.aurora.repository;

import com.codegym.aurora.entity.DayOfBirthNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfBirthNumberRepository extends JpaRepository<DayOfBirthNumber, Integer> {
    DayOfBirthNumber findDayOfBirthNumberByIndicators(int day);
}
