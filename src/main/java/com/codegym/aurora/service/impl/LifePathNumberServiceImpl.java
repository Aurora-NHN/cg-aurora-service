package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.LifePathNumberConverter;
import com.codegym.aurora.entity.LifePathNumber;
import com.codegym.aurora.payload.response.LifePathResponeDTO;
import com.codegym.aurora.repository.LifePathNumberRepository;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.util.NumeroloryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifePathNumberServiceImpl implements LifePathNumberService {
    private final LifePathNumberRepository lifePathNumberRepository;
    private final LifePathNumberConverter lifePathNumberConverter;

    @Override
    public LifePathResponeDTO calculateLifePathNumber(int day, int month, int year) {
        int calculatorLifePathNumber = calculateDayMonthYearSum(day, month, year);
        if (calculatorLifePathNumber == 11 || calculatorLifePathNumber == 22 || calculatorLifePathNumber == 33) {
            return getResponseDTO(calculatorLifePathNumber);
        }

        int reducedNumber = calculateReducedNumber(calculatorLifePathNumber);
        return getResponseDTO(reducedNumber);
    }

    private int calculateDayMonthYearSum(int day, int month, int year) {
        int daySum = NumeroloryUtils.calculateDigitSum(day);
        int monthSum = NumeroloryUtils.calculateDigitSum(month);
        int yearSum = NumeroloryUtils.calculateDigitSum(year);
        return daySum + monthSum + yearSum;
    }

    private int calculateReducedNumber(int number) {
        while (number > 9) {
            number = NumeroloryUtils.calculateDigitSum(number);
        }
        return number;
    }

    private LifePathResponeDTO getResponseDTO(int lifePathNumber) {
        LifePathNumber lifePathNumberEntity = lifePathNumberRepository.findLifePathNumberByIndicators(lifePathNumber);
        return lifePathNumberConverter.convertEntitytoDTO(lifePathNumberEntity);
    }
}
