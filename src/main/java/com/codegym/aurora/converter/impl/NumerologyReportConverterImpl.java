package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import com.codegym.aurora.payload.response.LifePathResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.service.LifePathNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NumerologyReportConverterImpl implements NumerologyReportConverter {
    private final UserRepository userRepository;
    private final LifePathNumberService lifePathNumberService;
    private final DayOfBirthNumberService dayOfBirthNumberService;

    @Override
    public NumerologyReportResponseDTO convertEntityToResponseDTO(NumerologyReport numerologyReport) {
        NumerologyReportResponseDTO numerologyReportResponseDTO = new NumerologyReportResponseDTO();
        BeanUtils.copyProperties(numerologyReport, numerologyReportResponseDTO);

        LifePathResponseDTO lifePathResponseDTO = lifePathNumberService.getLifePathNumber(numerologyReport.getLifePathNumber());
        numerologyReportResponseDTO.setLifePathResponseDTO(lifePathResponseDTO);

        DayOfBirthNumberResponseDTO dayOfBirthNumberResponseDTO = dayOfBirthNumberService.getDayOfBirthNumber(numerologyReport.getDayOfBirthNumber());
        numerologyReportResponseDTO.setDayOfBirthNumberResponseDTO(dayOfBirthNumberResponseDTO);

        //set các thộc tính còn lại của report tại đây
        return numerologyReportResponseDTO;
    }

    @Override
    public NumerologyReport convertRequestDtoToEntity(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        NumerologyReport numerologyReport = new NumerologyReport();

        int day = numerologyReportRequestDTO.getDayOfBirth();
        int month = numerologyReportRequestDTO.getMonthOfBirth();
        int year = numerologyReportRequestDTO.getYearOfBirth();

        BeanUtils.copyProperties(numerologyReportRequestDTO, numerologyReport);
        User user = userRepository.findUserById(numerologyReportRequestDTO.getUserId());
        numerologyReport.setUser(user);

        int lifePathNumber = lifePathNumberService.calculateLifePathNumber(day, month, year);
        numerologyReport.setLifePathNumber(lifePathNumber);

        int dayOfBirthNumber = dayOfBirthNumberService.calculateDayOfBirthNumber(day);
        numerologyReport.setDayOfBirthNumber(dayOfBirthNumber); // Không ăn

        // set các thuộc tính còn lại của report tại đây
        return numerologyReport;
    }
}
