package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.LifePathNumber;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.LifePathResponseDTO;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.util.NumeroloryConstants;
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
    public LifePathResponseDTO getLifePathNumber(Integer number) {
        return staticLifePathNumberList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số Chủ đạo phù hợp"));
    }

    @Override
    public LifePathResponseDTO findLifePathNumber(NumerologyReportRequestDTO requestDTO) {
        Integer day = requestDTO.getDayOfBirth();
        Integer year = requestDTO.getYearOfBirth();
        Integer month = requestDTO.getMonthOfBirth();
        Integer calculatorLifePathNumber = calculateDayMonthYearSum(day, month, year);
        if (calculatorLifePathNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
                calculatorLifePathNumber == NumeroloryConstants.MASTER_NUMBER_22 ||
                calculatorLifePathNumber == NumeroloryConstants.MASTER_NUMBER_33) {
            return getLifePathNumber(calculatorLifePathNumber);
        }
        int reducedNumber = calculateReducedNumber(calculatorLifePathNumber);
        return getLifePathNumber(reducedNumber);
    }

    @Override
    public LifePathResponseDTO findLifePathNumber(DataNumerologyReport data) {

        Integer day = data.getDayOfBirth();
        Integer year = data.getYearOfBirth();
        Integer month = data.getMonthOfBirth();
        Integer calculatorLifePathNumber = calculateDayMonthYearSum(day, month, year);
        if (calculatorLifePathNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
                calculatorLifePathNumber == NumeroloryConstants.MASTER_NUMBER_22 ||
                calculatorLifePathNumber == NumeroloryConstants.MASTER_NUMBER_33) {
            return getLifePathNumber(calculatorLifePathNumber);
        }
        Integer reducedNumber = calculateReducedNumber(calculatorLifePathNumber);
        return getLifePathNumber(reducedNumber);
    }

    private Integer calculateDayMonthYearSum(Integer day, Integer month, Integer year) {
        Integer daySum = NumeroloryUtils.calculateDigitSum(day);
        Integer monthSum = NumeroloryUtils.calculateDigitSum(month);
        Integer yearSum = NumeroloryUtils.calculateDigitSum(year);
        return daySum + monthSum + yearSum;
    }

    private int calculateReducedNumber(Integer number) {
        while (number > 9) {
            number = NumeroloryUtils.calculateDigitSum(number);
        }
        return number;
    }
}

