package com.codegym.aurora.service;

import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

import java.util.List;

public interface NumerologyReportService {
    ResponseDTO createNumerologyReportResponse(NumerologyReportRequestDTO numerologyReportRequestDTO);
    ResponseDTO findAllNumerologyReporForUser();
    int checkCount();
}
