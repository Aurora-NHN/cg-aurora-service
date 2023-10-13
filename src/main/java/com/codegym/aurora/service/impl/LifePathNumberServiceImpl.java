package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.LifePathNumber;
import com.codegym.aurora.payload.response.LifePathResponseDTO;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LifePathNumberServiceImpl implements LifePathNumberService {


    private static List<LifePathResponseDTO> staticLifePathNumberList = new ArrayList<>();

    static {
        try {
            staticLifePathNumberList = loadStaticLifePathList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<LifePathResponseDTO> loadStaticLifePathList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/life-path-number.json").getInputStream()) {
            LifePathNumber lifePathNumber = new ObjectMapper().readValue(inputStream, LifePathNumber.class);
            return lifePathNumber.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public LifePathResponseDTO getLifePathNumber(int number) {
        LifePathResponseDTO result = staticLifePathNumberList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số Chủ đạo phù hợp"));
        return result;
    }


    @Override
    public int calculateLifePathNumber(int day, int month, int year) {
        int calculatorLifePathNumber = calculateDayMonthYearSum(day, month, year);
        if (calculatorLifePathNumber == 11 || calculatorLifePathNumber == 22 || calculatorLifePathNumber == 33) {
            return getLifePathNumber(calculatorLifePathNumber).getNumber();
        }
        int reducedNumber = calculateReducedNumber(calculatorLifePathNumber);
        return getLifePathNumber(reducedNumber).getNumber();
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
}

