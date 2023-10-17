package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.PinnacleOfLife;
import com.codegym.aurora.payload.from_file.PinnacleOfLifeList;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTO;
import com.codegym.aurora.service.PinnacleOfLifeService;
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
public class PinnacleOfLifeServiceImpl implements PinnacleOfLifeService {
    private static List<PinnacleOfLifeResponseDTO> staticPinnacleOfLifeList = new ArrayList<>();
    private static final int SPECIAL_NUMBER_FOR_CALCULATE_AGE = 36;
    private static final int AGE_CYCLE = 9;
    static {
        try {
            staticPinnacleOfLifeList= loadStaticPinnacleOfLifeList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<PinnacleOfLifeResponseDTO> loadStaticPinnacleOfLifeList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/pinnacle-of-life.json").getInputStream()) {
            PinnacleOfLifeList pinnacleOfLifeList = new ObjectMapper().readValue(inputStream, PinnacleOfLifeList.class);
            return pinnacleOfLifeList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public PinnacleOfLifeResponseDTO getPinnacleOfLifeResponseDtoInStaticList(int pinnacleOfLifeNumber) {
        PinnacleOfLifeResponseDTO result = staticPinnacleOfLifeList.stream()
                .filter(dto -> dto.getNumber() == pinnacleOfLifeNumber)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số số đỉnh cao phù hợp phù hợp"));
        return result;
    }

    @Override
    public int calculatePinnacleOfLifeFirst(int month, int day) {
        int monthReduce = NumeroloryUtils.reduceToSingleDigit(month);
        int dayReduce = NumeroloryUtils.reduceToSingleDigit(day);
        return NumeroloryUtils.reduceToSingleDigit(monthReduce + dayReduce);
    }

    @Override
    public int calculatePinnacleOfLifeSecond(int day, int year) {
        int dayReduce = NumeroloryUtils.reduceToSingleDigit(day);
        int yearReduce = NumeroloryUtils.reduceToSingleDigit(year);
        return NumeroloryUtils.reduceToSingleDigit(dayReduce + yearReduce);
    }

    @Override
    public int calculatePinnacleOfLifeThird(int pinnacleOfLifeFirst, int pinnacleOfLifeSecond) {
        int sum = pinnacleOfLifeFirst + pinnacleOfLifeSecond;
        if (sum == 10 || sum == 11){
            return sum;
        }
        return NumeroloryUtils.reduceToSingleDigit(sum);
    }

    @Override
    public int calculatePinnacleOfLifeFour(int month, int year) {
        int reduceMonth = NumeroloryUtils.reduceToSingleDigit(month);
        int reduceYear = NumeroloryUtils.reduceToSingleDigit(year);
        int sum = reduceMonth + reduceYear;
        if (sum == 10 || sum == 11){
            return sum;
        }
        return NumeroloryUtils.reduceToSingleDigit(sum);
    }

    @Override
    public int calculateAgeForPinnacleOfLifeFirst(int lifePathNumber) {
        return SPECIAL_NUMBER_FOR_CALCULATE_AGE - lifePathNumber;
    }

    @Override
    public int calculateAgeForOrtherPinnacleOfLife(int age) {
        return age + AGE_CYCLE;
    }

    @Override
    public List<PinnacleOfLife> createPinnacOfLifeEntity(int day, int month, int year, int lifePathNumber) {
        List<PinnacleOfLife> pinnacleOfLifeList = new ArrayList<>();

        int pinnacleOfLifeFirstNumber = calculatePinnacleOfLifeFirst(month, day);
        int pinnacleOfLifeSecondNumber = calculatePinnacleOfLifeSecond(day, year);
        int pinnacleOfLifeThirdNumber = calculatePinnacleOfLifeThird(pinnacleOfLifeFirstNumber, pinnacleOfLifeSecondNumber);
        int pinnacleOfLifeFourNumber = calculatePinnacleOfLifeFour(month,year);

        int ageForPinnacleOfLifeFirst = calculateAgeForPinnacleOfLifeFirst(lifePathNumber);
        int ageForPinnacleOfLifeSecond = calculateAgeForOrtherPinnacleOfLife(ageForPinnacleOfLifeFirst);
        int ageForPinnacleOfLifeThird = calculateAgeForOrtherPinnacleOfLife(ageForPinnacleOfLifeSecond);
        int ageForPinnacleOfLifeFour = calculateAgeForOrtherPinnacleOfLife(ageForPinnacleOfLifeThird);

        PinnacleOfLife pinnacleOfLifeFirst = new PinnacleOfLife(pinnacleOfLifeFirstNumber, ageForPinnacleOfLifeFirst);
        PinnacleOfLife pinnacleOfLifeSecond = new PinnacleOfLife(pinnacleOfLifeSecondNumber, ageForPinnacleOfLifeSecond);
        PinnacleOfLife pinnacleOfLifeThird = new PinnacleOfLife(pinnacleOfLifeThirdNumber, ageForPinnacleOfLifeThird);
        PinnacleOfLife pinnacleOfLifeFour = new PinnacleOfLife(pinnacleOfLifeFourNumber, ageForPinnacleOfLifeFour);

        pinnacleOfLifeList.add(pinnacleOfLifeFirst);
        pinnacleOfLifeList.add(pinnacleOfLifeSecond);
        pinnacleOfLifeList.add(pinnacleOfLifeThird);
        pinnacleOfLifeList.add(pinnacleOfLifeFour);

        return pinnacleOfLifeList;
    }



}
