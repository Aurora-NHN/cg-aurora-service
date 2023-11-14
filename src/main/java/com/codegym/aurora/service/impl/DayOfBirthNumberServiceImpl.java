package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.DayOfBirthNumber;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.util.NumeroloryConstants;
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
    public DayOfBirthNumberResponseDTO getDayOfBirthNumber(Integer number) {
        DayOfBirthNumberResponseDTO result = staticDayOfBirthNumber.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số ngày sinh phù hợp"));
        return result;
    }

    @Override
    public Integer calculateDayOfBirthNumber(Integer day) {
        if (day == NumeroloryConstants.MASTER_NUMBER_11 ||
                day == NumeroloryConstants.MASTER_NUMBER_22) {
            return day;
        }
        return calculateReducedNumber(day);
    }

    @Override
    public DayOfBirthNumberResponseDTO findDayOfBirthNumber(DataNumerologyReport data) {
        Integer dayOfBirthNumber = calculateDayOfBirthNumber(data.getDayOfBirth());
        return getDayOfBirthNumber(dayOfBirthNumber);
    }

    private int calculateReducedNumber(Integer number) {
        while (number >10){
            number = NumeroloryUtils.calculateDigitSum(number);
        }
        return number;
    }
}
