package com.codegym.aurora.service;

import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;

public interface ChallengeNumberService {
    ChallengeNumberResponseDTO getChallengeNumberItem(int number);
    int calculateChallengeNumberFirst(int day, int month);
    int calculateChallengeNumberSecond(int day, int year);
    int calculateChallengeNumberThird(int challengeFirst, int challengeSecond);
    int calculateChallengeNumberFour(int month, int year);

    ChallengeNumber createChallengeNumberEntity(int day, int month, int year);

}
