package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.DayOfBirthNumber;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class DayOfBirthNumberServiceImpl implements DayOfBirthNumberService {
    private static List<DayOfBirthNumberResponseDTO> staticDayOfBirthNumber = new ArrayList<>();

    static {
        try {
            staticDayOfBirthNumber = loadStaticDayOfBirthList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<DayOfBirthNumberResponseDTO> loadStaticDayOfBirthList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/day-of-birth-number.json").getInputStream()) {
            DayOfBirthNumber dayOfBirthNumber = new ObjectMapper().readValue(inputStream, DayOfBirthNumber.class);
            return dayOfBirthNumber.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public DayOfBirthNumberResponseDTO getDayOfBirthNumber(int number) {
        DayOfBirthNumberResponseDTO result = staticDayOfBirthNumber.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số ngày sinh phù hợp"));
        return result;
    }

    @Override
    public int calculateDayOfBirthNumber(int day) {
        if (day == 11 || day == 22) {
            return getDayOfBirthNumber(day).getNumber();
        }
        int reduceNumber = calculateReducedNumber(day);
        return getDayOfBirthNumber(reduceNumber).getNumber();
    }

    private int calculateReducedNumber(int number) {
        while (number >10){
            number = NumeroloryUtils.calculateDigitSum(number);
        }
        return number;
    }
}
