package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.ChallengeNumberList;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
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
    public ChallengeNumberResponseDTO getChallengeNumberItem(Integer number) {
        ChallengeNumberResponseDTO result = staticChallengeNumberList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số thử thách phù hợp"));
        return result;
    }

    // các hàm viết mới
    @Override
    public Integer calculateChallengeFirst(DataNumerologyReport data) {
        Integer dayReduce = NumeroloryUtils.calculateDigitSum(data.getDayOfBirth());
        Integer monthSum = NumeroloryUtils.calculateDigitSum(data.getMonthOfBirth());
        return calculateResult(dayReduce, monthSum);

    }

    @Override
    public Integer calculateChallengeSecond(DataNumerologyReport data) {
        Integer dayReduce = NumeroloryUtils.calculateDigitSum(data.getDayOfBirth());
        Integer yearReduce = NumeroloryUtils.reduceNumber(data.getYearOfBirth());
        return calculateResult(dayReduce, yearReduce);
    }

    @Override
    public Integer calculateChallengeThird(Integer challengeFirst, Integer challengeSecond) {
        return calculateResult(challengeFirst, challengeSecond);
    }

    @Override
    public Integer calculateChallengeFour(DataNumerologyReport data) {
        Integer monthReduce = NumeroloryUtils.calculateDigitSum(data.getMonthOfBirth());
        Integer yearReduce = NumeroloryUtils.reduceNumber(data.getYearOfBirth());
        return calculateResult(monthReduce, yearReduce);
    }

    @Override
    public List<ChallengeNumberResponseDTO> createChallengeNumerList(DataNumerologyReport data) {
        List<ChallengeNumberResponseDTO> challengeNumberList = new ArrayList<>();
        Integer firstChallenge = calculateChallengeFirst(data);
        Integer secondChallenge = calculateChallengeSecond(data);
        Integer thirdChallenge = calculateChallengeThird(firstChallenge, secondChallenge);
        Integer fourChallenge = calculateChallengeFour(data);

        challengeNumberList.add(getChallengeNumberItem(firstChallenge));
        challengeNumberList.add(getChallengeNumberItem(secondChallenge));
        challengeNumberList.add(getChallengeNumberItem(thirdChallenge));
        challengeNumberList.add(getChallengeNumberItem(fourChallenge));
        return challengeNumberList;

    }

    private  Integer calculateResult(Integer numberFirst, Integer numberSecond){
        Integer result = numberFirst - numberSecond;
        if (result < 0){
            result = -result;
        }
        return result;
    }

}
