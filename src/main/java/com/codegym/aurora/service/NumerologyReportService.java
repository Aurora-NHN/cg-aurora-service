package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDtoForNumerologyReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface NumerologyReportService {

    DataNumerologyReport save(NumerologyReportRequestDTO numerologyReportRequestDTO, LocalDateTime createTime);
    ResponseDTO createNumerologyReport(NumerologyReportRequestDTO numerologyReportRequestDTO);
    NumerologyReportResponseDTO calculateFreeReport(NumerologyReportRequestDTO numerologyReportRequestDTO);
    NumerologyReportResponseDTO calculateVipReport(DataNumerologyReport data, UserResponseDtoForNumerologyReport user);

    Page<NumerologyReportResponseDTO> getNumerologyReportsPage(Pageable pageable);



}
