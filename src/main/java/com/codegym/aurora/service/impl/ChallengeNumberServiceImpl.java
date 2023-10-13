package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.payload.from_file.ChallengeNumberList;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
import com.codegym.aurora.repository.ChallengeNumberRepository;
import com.codegym.aurora.service.ChallengeNumberService;
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
public class ChallengeNumberServiceImpl implements ChallengeNumberService {
    private final ChallengeNumberRepository challengeNumberRepository;

    private static List<ChallengeNumberResponseDTO> staticChallengeNumberList = new ArrayList<>();

    static {
        try {
            staticChallengeNumberList = loadStaticChallengeNumberList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<ChallengeNumberResponseDTO> loadStaticChallengeNumberList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/challenge-number.json").getInputStream()) {
            ChallengeNumberList challengeNumberList = new ObjectMapper().readValue(inputStream, ChallengeNumberList.class);
            return challengeNumberList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public ChallengeNumberResponseDTO getChallengeNumberItem(int number) {
        ChallengeNumberResponseDTO result = staticChallengeNumberList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số thử thách phù hợp"));
        return result;
    }

    @Override
    public int calculateChallengeNumberFirst(int day, int month) {
        int daySum = NumeroloryUtils.calculateDigitSum(day);
        int monthSum = NumeroloryUtils.calculateDigitSum(month);
        return calculateResult(daySum, monthSum);
    }

    @Override
    public int calculateChallengeNumberSecond(int day, int year) {
        int dayReduce = NumeroloryUtils.calculateDigitSum(day);
        int yearReduce = NumeroloryUtils.reduceNumber(year);
        return calculateResult(dayReduce, yearReduce);
    }

    @Override
    public int calculateChallengeNumberThird(int challengeFirst, int challengeSecond) {
        return calculateResult(challengeFirst, challengeSecond);
    }

    @Override
    public int calculateChallengeNumberFour(int month, int year) {
        int monthReduce = NumeroloryUtils.calculateDigitSum(month);
        int yearReduce = NumeroloryUtils.reduceNumber(year);
        return calculateResult(monthReduce, yearReduce);
    }

    @Override
    public ChallengeNumber createChallengeNumberEntity(int day, int month, int year) {
        int challengeNumberFirst = calculateChallengeNumberFirst(day, month);
        int challengeNumberSecond = calculateChallengeNumberSecond(day, year);
        int challengeNumberThird = calculateChallengeNumberThird(challengeNumberFirst, challengeNumberSecond);
        int challengeNumberFour = calculateChallengeNumberFour(month, year);
        ChallengeNumber challengeNumber = ChallengeNumber.builder()
                .challengeNumberFirst(challengeNumberFirst)
                .challengeNumberSecond(challengeNumberSecond)
                .challengeNumberThird(challengeNumberThird)
                .challengeNumberFour(challengeNumberFour)
                .build();
        return challengeNumber;
    }

    private  int calculateResult(int numberFirst, int numberSecond){
        int result = numberFirst - numberSecond;
        if (result < 0){
            result = -result;
        }
        return result;
    }

}
