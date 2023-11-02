package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.LifePathResponseDTO;

public interface LifePathNumberService {

    LifePathResponseDTO getLifePathNumber(Integer number);

    LifePathResponseDTO findLifePathNumber(NumerologyReportRequestDTO requestDTO);
    LifePathResponseDTO findLifePathNumber(DataNumerologyReport data);

}
