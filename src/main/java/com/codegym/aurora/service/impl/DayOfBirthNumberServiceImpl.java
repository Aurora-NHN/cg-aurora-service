package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.DayOfBirthNumberConvert;
import com.codegym.aurora.entity.DayOfBirthNumber;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponeDTO;
import com.codegym.aurora.repository.DayOfBirthNumberRepository;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.util.NumeroloryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class DayOfBirthNumberServiceImpl  implements DayOfBirthNumberService {
    private final DayOfBirthNumberRepository dayOfBirthNumberRepository;
    private final DayOfBirthNumberConvert dayOfBirthNumberConvert;

    @Override
    public DayOfBirthNumberResponeDTO getDayOfBirthNumber(int day) {
        if (day == 11 || day == 22) {
            return getResponeDTO(day);
        }
        int reduceNumber = calculateReducedNumber(day);
        return getResponeDTO(reduceNumber);
    }


    private DayOfBirthNumberResponeDTO getResponeDTO(int day){
        DayOfBirthNumber dayOfBirthNumber = dayOfBirthNumberRepository.findDayOfBirthNumberByIndicators(day);
        return dayOfBirthNumberConvert.convertEntityToResponeDTO(dayOfBirthNumber);
    }

    private int calculateReducedNumber(int number) {
        while (number >10){
            number = NumeroloryUtils.calculateDigitSum(number);
        }
        return number;
    }
}
