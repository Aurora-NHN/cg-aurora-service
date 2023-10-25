package com.codegym.aurora.converter;

import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.FreeNumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;

public interface NumerologyReportConverter {
    NumerologyReport convertRequestDtoToEntity(NumerologyReportRequestDTO numerologyReportRequestDTO);

    NumerologyReport convertRequestDtoToEntityForFreeNumber(NumerologyReportRequestDTO numerologyReportRequestDTO);

    NumerologyReportResponseDTO convertEntityToNumerologyReportForFreeVersion(NumerologyReport numerologyReport);
    NumerologyReportResponseDTO convertEntityToResponseDTO(NumerologyReport numerologyReport);



}
