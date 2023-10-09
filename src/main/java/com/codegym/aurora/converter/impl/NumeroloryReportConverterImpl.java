package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.NumeroloryReportConverter;
import com.codegym.aurora.entity.NumeroloryReport;
import com.codegym.aurora.payload.request.NumeroloryReportRequestDTO;
import com.codegym.aurora.payload.response.NumeroloryReportResponeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NumeroloryReportConverterImpl implements NumeroloryReportConverter {
    @Override
    public NumeroloryReportResponeDTO convertEntityToResponseDTO(NumeroloryReport numeroloryReport) {
        NumeroloryReportResponeDTO numeroloryReportResponeDTO = new NumeroloryReportResponeDTO();
        BeanUtils.copyProperties(numeroloryReport,numeroloryReportResponeDTO);
        return numeroloryReportResponeDTO;
    }

    @Override
    public NumeroloryReport convertRequestDtoToEntity(NumeroloryReportRequestDTO numeroloryReportRequestDTO) {
        NumeroloryReport numeroloryReport = new NumeroloryReport();
        BeanUtils.copyProperties(numeroloryReportRequestDTO, numeroloryReport);
        return numeroloryReport;
    }
}
