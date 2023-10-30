package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.YoungStateNumberResponseDTO;

public interface YoungStateNumberService {
    YoungStateNumberResponseDTO getYoungStateNumber(Integer number);

    YoungStateNumberResponseDTO findYoungStateNumber(DataNumerologyReport data);


}

