package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.ChallengeNumberConverter;
import com.codegym.aurora.converter.LifePhaseConverter;
import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.converter.PersonalYearConverter;
import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.entity.LifePhase;
import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import com.codegym.aurora.payload.response.LifePathResponseDTO;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.service.AttitudeNumberService;
import com.codegym.aurora.service.ChallengeNumberService;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.service.LifePhaseService;
import com.codegym.aurora.service.PersonalYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NumerologyReportConverterImpl implements NumerologyReportConverter {
    private final LifePathNumberService lifePathNumberService;
    private final DayOfBirthNumberService dayOfBirthNumberService;
    private final AttitudeNumberService attitudeNumberService;
    private final LifePhaseService lifePhaseService;
    private  final LifePhaseConverter lifePhaseConverter;
    private final ChallengeNumberService challengeNumberService;
    private final ChallengeNumberConverter challengeNumberConverter;
    private  final PersonalYearService personalYearService;
    private final PersonalYearConverter personalYearConverter;
    @Override
    public NumerologyReportResponseDTO convertEntityToResponseDTO(NumerologyReport numerologyReport) {
        NumerologyReportResponseDTO numerologyReportResponseDTO = new NumerologyReportResponseDTO();
        BeanUtils.copyProperties(numerologyReport, numerologyReportResponseDTO);

        LifePathResponseDTO lifePathResponseDTO = lifePathNumberService.
                getLifePathNumber(numerologyReport.getLifePathNumber());
        numerologyReportResponseDTO.setLifePathResponseDTO(lifePathResponseDTO);

        DayOfBirthNumberResponseDTO dayOfBirthNumberResponseDTO = dayOfBirthNumberService.
                getDayOfBirthNumber(numerologyReport.getDayOfBirthNumber());
        numerologyReportResponseDTO.setDayOfBirthNumberResponseDTO(dayOfBirthNumberResponseDTO);

        AttitudeNumberResponseDTO attitudeNumberResponseDTO = attitudeNumberService.
                getAttitudeNumber(numerologyReport.getAttitudeNumber());
        numerologyReportResponseDTO.setAttitudeNumberResponseDTO(attitudeNumberResponseDTO);

        LifePhaseResponseDTO lifePhaseResponseDTO = lifePhaseConverter
                .convertEntityToResponseDto(numerologyReport.getLifePhase());
        numerologyReportResponseDTO.setLifePhaseResponseDTO(lifePhaseResponseDTO);
        //set các thộc tính còn lại của report tại đây
        List<ChallengeNumberResponseDTO> challengeNumberResponseDTOList = challengeNumberConverter
                .convertEntityToResponseDto(numerologyReport.getChallengeNumber());
        numerologyReportResponseDTO.setChallengeNumberResponseDTOList(challengeNumberResponseDTOList);

        List<PersonalYearResponseDTO> personalYearResponseDTOList = personalYearConverter
                .convertEntityToResponeDtoList(numerologyReport.getPersonalYearList());
        numerologyReportResponseDTO.setPersonalYearResponseDTOList(personalYearResponseDTOList);
        return numerologyReportResponseDTO;
    }

    @Override
    public NumerologyReport convertRequestDtoToEntity(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        NumerologyReport numerologyReport = new NumerologyReport();

        int day = numerologyReportRequestDTO.getDayOfBirth();
        int month = numerologyReportRequestDTO.getMonthOfBirth();
        int year = numerologyReportRequestDTO.getYearOfBirth();

        BeanUtils.copyProperties(numerologyReportRequestDTO, numerologyReport);

        int lifePathNumber = lifePathNumberService.calculateLifePathNumber(day, month, year);
        numerologyReport.setLifePathNumber(lifePathNumber);

        int dayOfBirthNumber = dayOfBirthNumberService.calculateDayOfBirthNumber(day);
        numerologyReport.setDayOfBirthNumber(dayOfBirthNumber);

        int attitudeNumber = attitudeNumberService.calculateAttitudeNumber(day, month);
        numerologyReport.setAttitudeNumber(attitudeNumber);

        LifePhase lifePhase = lifePhaseService.caculateLifephase(day, month, year);
        numerologyReport.setLifePhase(lifePhase);

        ChallengeNumber challengeNumber = challengeNumberService.createChallengeNumberEntity(day, month, year);
        numerologyReport.setChallengeNumber(challengeNumber);

        List<PersonalYear> personalYearList = personalYearService.createPersonalYearEntity(attitudeNumber);
        numerologyReport.setPersonalYearList(personalYearList);
        // set các thuộc tính còn lại của report tại đây

        return numerologyReport;
    }
}
