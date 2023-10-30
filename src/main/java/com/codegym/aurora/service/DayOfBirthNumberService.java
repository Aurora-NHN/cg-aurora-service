package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;

public interface DayOfBirthNumberService {
    DayOfBirthNumberResponseDTO getDayOfBirthNumber(Integer number);

    Integer calculateDayOfBirthNumber(Integer day);

    DayOfBirthNumberResponseDTO findDayOfBirthNumber(DataNumerologyReport data);
}
