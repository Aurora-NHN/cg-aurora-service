package com.codegym.aurora.converter;

import com.codegym.aurora.entity.NumeroloryReport;
import com.codegym.aurora.payload.request.NumeroloryReportRequestDTO;
import com.codegym.aurora.payload.response.NumeroloryReportResponeDTO;

public interface NumeroloryReportConverter {
    NumeroloryReportResponeDTO convertEntityToResponseDTO(NumeroloryReport numeroloryReport);
    NumeroloryReport convertRequestDtoToEntity(NumeroloryReportRequestDTO numeroloryReportRequestDTO);
}
