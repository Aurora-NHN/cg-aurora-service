package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.BalanceNumberConverter;
import com.codegym.aurora.converter.ChallengeNumberConverter;
import com.codegym.aurora.converter.LifePhaseConverter;
import com.codegym.aurora.converter.MiddleAgedNumberConverter;
import com.codegym.aurora.converter.MissionNumberConverter;
import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.converter.PinnacleOfLifeConverter;
import com.codegym.aurora.converter.SoulNumberConverter;
import com.codegym.aurora.converter.UserConverter;
import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.entity.LifePhase;
import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.entity.PinnacleOfLife;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;
import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import com.codegym.aurora.payload.response.FreeNumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.LifePathResponseDTO;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;
import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;
import com.codegym.aurora.payload.response.MissionNumberResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDtoForReport;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTOForReport;
import com.codegym.aurora.payload.response.SoulNumberResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDtoForNumerologyReport;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.AttitudeNumberService;
import com.codegym.aurora.service.BalanceNumberService;
import com.codegym.aurora.service.ChallengeNumberService;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.service.LifePhaseService;
import com.codegym.aurora.service.MiddleAgedNumberService;
import com.codegym.aurora.service.MissionNumberService;
import com.codegym.aurora.service.PersonalYearService;
import com.codegym.aurora.service.PinnacleOfLifeService;
import com.codegym.aurora.service.SoulNumberService;
import com.codegym.aurora.service.UserService;
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
    private final MissionNumberService missionNumberService;
    private final MissionNumberConverter missionNumberConverter;
    private final SoulNumberService soulNumberService;
    private final SoulNumberConverter soulNumberConverter;
    private final MiddleAgedNumberConverter middleAgedNumberConverter;
    private final MiddleAgedNumberService middleAgedNumberService;
    private final PinnacleOfLifeService pinnacleOfLifeService;
    private final PinnacleOfLifeConverter pinnacleOfLifeConverter;
    private final BalanceNumberService balanceNumberService;
    private final BalanceNumberConverter balanceNumberConverter;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public NumerologyReportResponseDTO convertEntityToResponseDTO(NumerologyReport numerologyReport) {
        NumerologyReportResponseDTO numerologyReportResponseDTO = new NumerologyReportResponseDTO();
        BeanUtils.copyProperties(numerologyReport, numerologyReportResponseDTO);

        User user = userRepository.findUserById(numerologyReport.getUser().getId());
        numerologyReportResponseDTO.setUserResponseDtoForNumerologyReport(userConverter
                .convertEntityToUserForNumerologyReportResponseDTO(user));

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

        List<ChallengeNumberResponseDTO> challengeNumberResponseDTOList = challengeNumberConverter
                .convertEntityToResponseDto(numerologyReport.getChallengeNumber());
        numerologyReportResponseDTO.setChallengeNumberResponseDTOList(challengeNumberResponseDTOList);

        List<PersonalYearResponseDtoForReport> personalYearResponseDTOList = personalYearService
                .createPersonalYearResponseDtoForReport(numerologyReport.getPersonalYearList());
        numerologyReportResponseDTO.setPersonalYearResponseDTOList(personalYearResponseDTOList);


        MissionNumberResponseDTO missionNumberResponeDTO = missionNumberConverter
                .convertEntityToResponseDTO(numerologyReport.getMissionNumber());
        numerologyReportResponseDTO.setMissionNumberResponseDtTO(missionNumberResponeDTO);

        SoulNumberResponseDTO soulNumberResponseDTO = soulNumberConverter
                .convertSoulNumberToResponseDto(numerologyReport.getSoulNumber());
        numerologyReportResponseDTO.setSoulNumberResponseDTO(soulNumberResponseDTO);

        MiddleAgedNumberResponseDto middleAgedNumberResponseDto = middleAgedNumberConverter
                .convertEntityToResponseDto(numerologyReport.getMiddleAgedNumber());
        numerologyReportResponseDTO.setMiddleAgedNumberResponseDto(middleAgedNumberResponseDto);

        List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeResponseDTOForReportList = pinnacleOfLifeConverter
                .convertEntityToResponseDto(numerologyReport.getPinnacleOfLifeList());
        numerologyReportResponseDTO.setPinnacleOfLifeResponseDTOForReportList(pinnacleOfLifeResponseDTOForReportList);
        //set các thộc tính còn lại của report tại đây
        BalanceNumberResponseDTO balanceNumberResponseDTO = balanceNumberConverter
                .convertEntityToResponseDto(numerologyReport.getBalanceNumber());
        numerologyReportResponseDTO.setBalanceNumberResponseDTO(balanceNumberResponseDTO);

        return numerologyReportResponseDTO;
    }

    @Override
    public NumerologyReport convertRequestDtoToEntity(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        NumerologyReport numerologyReport = new NumerologyReport();

        int day = numerologyReportRequestDTO.getDayOfBirth();
        int month = numerologyReportRequestDTO.getMonthOfBirth();
        int year = numerologyReportRequestDTO.getYearOfBirth();
        String fullName = numerologyReportRequestDTO.getFullName();

        BeanUtils.copyProperties(numerologyReportRequestDTO, numerologyReport);

        User user = userRepository.findByUsername(userService.getCurrentUsername());
        numerologyReport.setUser(user);

        int lifePathNumber = lifePathNumberService.calculateLifePathNumber(day, month, year);
        numerologyReport.setLifePathNumber(lifePathNumber);

        int dayOfBirthNumber = dayOfBirthNumberService.calculateDayOfBirthNumber(day);
        numerologyReport.setDayOfBirthNumber(dayOfBirthNumber);

        int attitudeNumber = attitudeNumberService.calculateAttitudeNumber(day, month);
        numerologyReport.setAttitudeNumber(attitudeNumber);

        LifePhase lifePhase = lifePhaseService.createLifePhase(day, month, year);
        numerologyReport.setLifePhase(lifePhase);


        ChallengeNumber challengeNumber = challengeNumberService.createChallengeNumberEntity(day, month, year);
        numerologyReport.setChallengeNumber(challengeNumber);

        List<PersonalYear> personalYearList = personalYearService.createPersonalYearEntity(attitudeNumber);
        numerologyReport.setPersonalYearList(personalYearList);

        int missionNumber = missionNumberService.calculateMissionNumber(fullName);
        numerologyReport.setMissionNumber(missionNumber);

        int soulNumber = soulNumberService.calculateSoulNumber(fullName);
        numerologyReport.setSoulNumber(soulNumber);

        int middleAgeNumber = middleAgedNumberService.calculateMiddleAgedNumber(missionNumber,lifePathNumber);
        numerologyReport.setMiddleAgedNumber(middleAgeNumber);

        List<PinnacleOfLife> pinnacleOfLifeList = pinnacleOfLifeService.createPinnacOfLifeEntity(day,month,year, lifePathNumber);
        numerologyReport.setPinnacleOfLifeList(pinnacleOfLifeList);
        // set các thuộc tính còn lại của report tại đây
        int ballanceNumber = balanceNumberService.calculateBalanceNumber(fullName);
        numerologyReport.setBalanceNumber(ballanceNumber);

        return numerologyReport;
    }

    @Override
    public NumerologyReport convertRequestDtoToEntityForFreeNumber(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        NumerologyReport numerologyReport = new NumerologyReport();

        int day = numerologyReportRequestDTO.getDayOfBirth();
        int month = numerologyReportRequestDTO.getMonthOfBirth();
        int year = numerologyReportRequestDTO.getYearOfBirth();
        String fullName = numerologyReportRequestDTO.getFullName();

        BeanUtils.copyProperties(numerologyReportRequestDTO, numerologyReport);
        int lifePathNumber = lifePathNumberService.calculateLifePathNumber(day, month, year);
        numerologyReport.setLifePathNumber(lifePathNumber);
        int missionNumber = missionNumberService.calculateMissionNumber(fullName);
        numerologyReport.setMissionNumber(missionNumber);
        int attitudeNumber = attitudeNumberService.calculateAttitudeNumber(day, month);
        numerologyReport.setAttitudeNumber(attitudeNumber);
        return numerologyReport;
    }

    @Override
    public FreeNumerologyReportResponseDTO convertEntityToFreeNumerologyReportResponseDTO(NumerologyReport numerologyReport) {
        FreeNumerologyReportResponseDTO freeNumerologyReportResponseDTO = new FreeNumerologyReportResponseDTO();
        BeanUtils.copyProperties(numerologyReport,freeNumerologyReportResponseDTO);

        LifePathResponseDTO lifePathResponseDTO = lifePathNumberService.
                getLifePathNumber(numerologyReport.getLifePathNumber());
        freeNumerologyReportResponseDTO.setLifePathResponseDTO(lifePathResponseDTO);


        AttitudeNumberResponseDTO attitudeNumberResponseDTO = attitudeNumberService.
                getAttitudeNumber(numerologyReport.getAttitudeNumber());
       freeNumerologyReportResponseDTO.setAttitudeNumberResponseDTO(attitudeNumberResponseDTO);

        MissionNumberResponseDTO missionNumberResponseDTO = missionNumberConverter
                .convertEntityToResponseDTO(numerologyReport.getMissionNumber());
       freeNumerologyReportResponseDTO.setMissionNumberResponseDtTO(missionNumberResponseDTO);

        return freeNumerologyReportResponseDTO;
    }
}
