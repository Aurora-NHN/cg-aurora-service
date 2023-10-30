package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;

public interface BalanceNumberService {
    BalanceNumberResponseDTO getBalanceNumberResponseDtoInStatic(Integer balanceNumber);

    Integer calculateBalanceNumber(DataNumerologyReport data);

    BalanceNumberResponseDTO findBalanceNumber(DataNumerologyReport data);
}
