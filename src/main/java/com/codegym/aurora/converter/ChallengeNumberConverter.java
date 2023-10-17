package com.codegym.aurora.converter;

import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;

import java.util.List;

public interface ChallengeNumberConverter {
    List<ChallengeNumberResponseDTO> convertEntityToResponseDto(ChallengeNumber challengeNumber);
}
