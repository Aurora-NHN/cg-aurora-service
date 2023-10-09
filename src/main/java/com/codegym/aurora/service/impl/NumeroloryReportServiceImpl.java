package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.NumeroloryReportConverter;
import com.codegym.aurora.entity.NumeroloryReport;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.NumeroloryReportRepository;
import com.codegym.aurora.service.NumeroloryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NumeroloryReportServiceImpl implements NumeroloryReportService {
    private final NumeroloryReportRepository numeroloryReportRepository;
    private final NumeroloryReportConverter numeroloryReportConverter;


    @Override
    public ResponseDTO getNumeroloryReportResponeDTO(NumeroloryReport numeroloryReport) {

        return null;
    }
}
