package com.codegym.aurora.converter;

import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;

public interface NumerologyReportConverter {
    NumerologyReportResponseDTO convertEntityToResponseDTO(NumerologyReport numerologyReport);
    NumerologyReport convertRequestDtoToEntity(NumerologyReportRequestDTO numerologyReportRequestDTO);

}
