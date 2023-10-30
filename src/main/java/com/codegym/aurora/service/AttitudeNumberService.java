package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;

public interface AttitudeNumberService {
    AttitudeNumberResponseDTO getAttitudeNumber(Integer number);

    AttitudeNumberResponseDTO findAttitudeNumber(NumerologyReportRequestDTO requestDTO);
    AttitudeNumberResponseDTO findAttitudeNumber(DataNumerologyReport data);
}
