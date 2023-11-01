package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.response.OldStateNumberResponseDTO;

public interface OldStateNumberService {
    OldStateNumberResponseDTO getOldStateNumber(Integer number);

    OldStateNumberResponseDTO findOldStateNumber(DataNumerologyReport data);
}
