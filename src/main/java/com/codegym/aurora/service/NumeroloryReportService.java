package com.codegym.aurora.service;

import com.codegym.aurora.entity.NumeroloryReport;
import com.codegym.aurora.payload.response.ResponseDTO;

public interface NumeroloryReportService {
    ResponseDTO getNumeroloryReportResponeDTO(NumeroloryReport numeroloryReport);
}
