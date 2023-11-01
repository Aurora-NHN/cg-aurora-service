package com.codegym.aurora.converter;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;

public interface NumerologyReportConverter {

    DataNumerologyReport converRequestToEntiy(NumerologyReportRequestDTO numerologyReportRequestDTO);

    NumerologyReportResponseDTO convertRequestToReportResponse(NumerologyReportRequestDTO requestDTO);

    NumerologyReportResponseDTO convertEntityToResponse(DataNumerologyReport dataNumerologyReport);

}
