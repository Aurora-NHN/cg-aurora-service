package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.MeanOfNumberResponseDTO;

import java.util.List;

public interface MeanOfNumberService {
    MeanOfNumberResponseDTO getMeanOfNumberResponseDto(String name);
    List<MeanOfNumberResponseDTO> getMeanOfAllNumberResponseDto();
}
