package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.SoulNumberResponseDTO;

public interface SoulNumberService {
    SoulNumberResponseDTO getSoulNumberResponseDtoInStatic(Integer soulNumber);
    Integer calculateSoulNumber(String fullName);

    SoulNumberResponseDTO findSoulNumber(DataNumerologyReport data);

}
