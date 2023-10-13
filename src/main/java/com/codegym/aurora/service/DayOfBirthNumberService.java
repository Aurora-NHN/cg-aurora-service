package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;

public interface DayOfBirthNumberService {
    DayOfBirthNumberResponseDTO getDayOfBirthNumber(int number);

    int calculateDayOfBirthNumber(int day);
}
