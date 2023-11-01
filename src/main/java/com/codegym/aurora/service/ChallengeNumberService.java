package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;

import java.util.List;

public interface ChallengeNumberService {
    ChallengeNumberResponseDTO getChallengeNumberItem(Integer number);
    Integer calculateChallengeFirst(DataNumerologyReport data);
    Integer calculateChallengeSecond(DataNumerologyReport data);
    Integer calculateChallengeThird(Integer challengeFirst, Integer challengeSecond);
    Integer calculateChallengeFour(DataNumerologyReport data);

    List<ChallengeNumberResponseDTO> createChallengeNumerList(DataNumerologyReport data);
}
