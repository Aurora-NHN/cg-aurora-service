package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NumerologyReportConverterImpl implements NumerologyReportConverter {

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public DataNumerologyReport converRequestToEntiy(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        DataNumerologyReport dataNumerologyReport = new DataNumerologyReport();
        BeanUtils.copyProperties(numerologyReportRequestDTO, dataNumerologyReport);
        dataNumerologyReport.setDeleted(false);
        dataNumerologyReport.setActivated(true);
        dataNumerologyReport.setUser(user);
        return dataNumerologyReport;
    }

    @Override
    public NumerologyReportResponseDTO convertRequestToReportResponse(NumerologyReportRequestDTO requestDTO) {
        NumerologyReportResponseDTO freeReport = new NumerologyReportResponseDTO();
        BeanUtils.copyProperties(requestDTO, freeReport);
        return freeReport;
    }

    @Override
    public NumerologyReportResponseDTO convertEntityToResponse(DataNumerologyReport dataNumerologyReport) {

        NumerologyReportResponseDTO responseDTO = new NumerologyReportResponseDTO();
        responseDTO.setId(dataNumerologyReport.getId());
        responseDTO.setFullName(dataNumerologyReport.getFullName());
        responseDTO.setNickName(dataNumerologyReport.getNickname());
        responseDTO.setDayOfBirth(dataNumerologyReport.getDayOfBirth());
        responseDTO.setMonthOfBirth(dataNumerologyReport.getMonthOfBirth());
        responseDTO.setYearOfBirth(dataNumerologyReport.getYearOfBirth());
        responseDTO.setCreateTime(dataNumerologyReport.getCreateTime());
        return responseDTO;
    }


}
