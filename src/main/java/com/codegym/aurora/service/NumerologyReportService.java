package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

public interface NumerologyReportService {
    ResponseDTO createNumerologyReportResponeDTO(NumerologyReportRequestDTO numerologyReportRequestDTO);

    ResponseDTO createFreeNumerologyReportResponeDTO(NumerologyReportRequestDTO numerologyReportRequestDTO);

    int checkCount();

    ResponseDTO createFullNumerologyReportResponseDTO(NumerologyReportRequestDTO numerologyReportRequestDTO);

}
