package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.MatureStateNumberResponseDTO;

public interface MatureStateNumberService {
   MatureStateNumberResponseDTO getMatureStateNumber(Integer number);

   MatureStateNumberResponseDTO findMatureStateNumer(DataNumerologyReport data);
}
