package com.codegym.aurora.service;

import com.codegym.aurora.entity.PersonalMonth;
import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;

import java.util.List;

public interface PersonalMonthService {
    PersonalMonthResponseDTO getPersonalMonthItem(int number);
    int calculatePersonalMonth(int personalYear, int month);
    List<PersonalMonth> createPersonalMonthEntityByPersonalYear(int personalYear);
    void saveByPersonalYearId(Long id);
}
