package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.ChallengeNumberConverter;
import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
import com.codegym.aurora.service.ChallengeNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengeNumberConverterImpl implements ChallengeNumberConverter {
    private final ChallengeNumberService challengeNumberService;

    @Override
    public List<ChallengeNumberResponseDTO> convertEntityToResponseDto(ChallengeNumber challengeNumber) {
        List<ChallengeNumberResponseDTO> challengeNumberResponseDTOList = new ArrayList<>();
        challengeNumberResponseDTOList.add(challengeNumberService
                .getChallengeNumberItem(challengeNumber.getChallengeNumberFirst()));
        challengeNumberResponseDTOList.add(challengeNumberService
                .getChallengeNumberItem(challengeNumber.getChallengeNumberSecond()));
        challengeNumberResponseDTOList.add(challengeNumberService
                .getChallengeNumberItem(challengeNumber.getChallengeNumberThird()));
        challengeNumberResponseDTOList.add(challengeNumberService
                .getChallengeNumberItem(challengeNumber.getChallengeNumberFour()));
        return challengeNumberResponseDTOList;
    }
}
