package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.converter.UserConverter;
import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;
import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;
import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import com.codegym.aurora.payload.response.LifePathResponseDTO;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;
import com.codegym.aurora.payload.response.MeanOfNumberResponseDTO;
import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;
import com.codegym.aurora.payload.response.MissionNumberResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDtoForReport;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTOForReport;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.response.SoulNumberResponseDTO;
import com.codegym.aurora.payload.response.UserResponseDtoForNumerologyReport;
import com.codegym.aurora.repository.DataNumerologyReportRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.AttitudeNumberService;
import com.codegym.aurora.service.BalanceNumberService;
import com.codegym.aurora.service.ChallengeNumberService;
import com.codegym.aurora.service.DayOfBirthNumberService;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.service.LifePhaseService;
import com.codegym.aurora.service.MeanOfNumberService;
import com.codegym.aurora.service.MiddleAgedNumberService;
import com.codegym.aurora.service.MissionNumberService;
import com.codegym.aurora.service.NumerologyReportService;
import com.codegym.aurora.service.PersonalYearService;
import com.codegym.aurora.service.PinnacleOfLifeService;
import com.codegym.aurora.service.SoulNumberService;
import com.codegym.aurora.service.UserService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NumerologyReportServiceImpl implements NumerologyReportService {
    private final NumerologyReportConverter numerologyReportConverter;
    private final UserService userService;
    private final UserRepository userRepository;
    private final DataNumerologyReportRepository dataNumerologyReportRepository;
    private final LifePathNumberService lifePathNumberService;
    private final AttitudeNumberService attitudeNumberService;
    private final MeanOfNumberService meanOfNumberService;
    private final MissionNumberService missionNumberService;
    private final DayOfBirthNumberService dayOfBirthNumberService;
    private final SoulNumberService soulNumberService;
    private final MiddleAgedNumberService middleAgedNumberService;
    private final BalanceNumberService balanceNumberService;
    private final LifePhaseService lifePhaseService;
    private final ChallengeNumberService challengeNumberService;
    private final PersonalYearService personalYearService;
    private final PinnacleOfLifeService pinnacleOfLifeService;
    private final UserConverter userConverter;

    @Override
    public  DataNumerologyReport  save(NumerologyReportRequestDTO numerologyReportRequestDTO, LocalDateTime createTime) {
        DataNumerologyReport dataNumerologyReport = numerologyReportConverter
                .converRequestToEntiy(numerologyReportRequestDTO);
        dataNumerologyReport.setCreateTime(createTime);
        return dataNumerologyReportRepository.save(dataNumerologyReport);
    }

    @Override
    public ResponseDTO createNumerologyReport(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        HttpStatus status;
        NumerologyReportResponseDTO numerologyReportResponseDTO = null;
        String message;
        LocalDateTime createTime = LocalDateTime.now();
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        if (numerologyReportRequestDTO.getVip()) {

            int count = user.getCount();

            if (user == null || count <= 0) {
                status = HttpStatus.PAYMENT_REQUIRED;
                message = "Vui lòng mua VIP để xem báo cáo với đầy đủ các chỉ số!";
            } else {
                count = count - 1;
                user.setCount(count);
                if (count == 0){
                    user.setCount(0);
                }
                userRepository.save(user);
                DataNumerologyReport  dataNumerologyReport = save(numerologyReportRequestDTO, createTime);
                UserResponseDtoForNumerologyReport userResponseDto = userConverter
                        .convertEntityToUserForNumerologyReportResponseDTO(user);
                numerologyReportResponseDTO = calculateVipReport(dataNumerologyReport, userResponseDto);

                status = HttpStatus.CREATED;
                message = "Create vip numerology report successfully!";
            }
            return createResponseDTO(status, message, numerologyReportResponseDTO);

        } else {
            numerologyReportResponseDTO = calculateFreeReport(numerologyReportRequestDTO);
            return createResponseDTO(
                    HttpStatus.OK,
                    "Create free version for numerology report successfully!",
                    numerologyReportResponseDTO);
        }

    }

    @Override
    public NumerologyReportResponseDTO calculateFreeReport(NumerologyReportRequestDTO requestDTO) {

        NumerologyReportResponseDTO freeReport = numerologyReportConverter
                .convertRequestToReportResponse(requestDTO);

        LifePathResponseDTO lifePathNumber= lifePathNumberService.
                findLifePathNumber(requestDTO);
        freeReport.setLifePathResponseDTO(lifePathNumber);

        AttitudeNumberResponseDTO attitudeNumber = attitudeNumberService.
                findAttitudeNumber(requestDTO);
        freeReport.setAttitudeNumberResponseDTO(attitudeNumber);

        MissionNumberResponseDTO missionNumber= missionNumberService
                .findMissionNumber(requestDTO);
        freeReport.setMissionNumberResponseDtTO(missionNumber);

        List<MeanOfNumberResponseDTO> meanOfNumberResponseDTOList = meanOfNumberService
                .getMeanOfAllNumberResponseDto();
       freeReport.setMeanOfNumberResponseDTOList(meanOfNumberResponseDTOList);

        return freeReport;
    }

    @Override
    public NumerologyReportResponseDTO calculateVipReport(DataNumerologyReport data,
                                                          UserResponseDtoForNumerologyReport user) {
        NumerologyReportResponseDTO vipReport = numerologyReportConverter
                .convertEntityToResponse(data);
        vipReport.setUserResponseDtoForNumerologyReport(user);

        LifePathResponseDTO lifePathNumerDTO = lifePathNumberService.
                findLifePathNumber(data);
        vipReport.setLifePathResponseDTO(lifePathNumerDTO);

        AttitudeNumberResponseDTO attitudeNumberDTO = attitudeNumberService.
                findAttitudeNumber(data);
        vipReport.setAttitudeNumberResponseDTO(attitudeNumberDTO);

        MissionNumberResponseDTO missionNumberDTO = missionNumberService
                .findMissionNumber(data);
        vipReport.setMissionNumberResponseDtTO(missionNumberDTO);

        List<MeanOfNumberResponseDTO> meanOfNumberList= meanOfNumberService
                .getMeanOfAllNumberResponseDto();
        vipReport.setMeanOfNumberResponseDTOList(meanOfNumberList);

        DayOfBirthNumberResponseDTO dayOfBirthNumber = dayOfBirthNumberService
                .findDayOfBirthNumber(data);
        vipReport.setDayOfBirthNumberResponseDTO(dayOfBirthNumber);

        SoulNumberResponseDTO soulNumber = soulNumberService.findSoulNumber(data);
        vipReport.setSoulNumberResponseDTO(soulNumber);

        Integer lifePathNumer = lifePathNumerDTO.getNumber();
        Integer missionNumber = missionNumberDTO.getNumber();
        MiddleAgedNumberResponseDto middleAgedNumber = middleAgedNumberService
                .findMiddleAgeNumber(missionNumber, lifePathNumer);
        vipReport.setMiddleAgedNumberResponseDto(middleAgedNumber);

        BalanceNumberResponseDTO balanceNumber = balanceNumberService.findBalanceNumber(data);
        vipReport.setBalanceNumberResponseDTO(balanceNumber);

        LifePhaseResponseDTO lifePhase = lifePhaseService.createLifePhase(data);
        vipReport.setLifePhaseResponseDTO(lifePhase);

        List<ChallengeNumberResponseDTO> challengeNumberList = challengeNumberService
                .createChallengeNumerList(data);
        vipReport.setChallengeNumberResponseDTOList(challengeNumberList);

        Integer attitudeNumber = attitudeNumberDTO.getNumber();
        List<PersonalYearResponseDtoForReport> personalYearList = personalYearService
                .createPersonalYearList(attitudeNumber);
        vipReport.setPersonalYearResponseDTOList(personalYearList);

        List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeList = pinnacleOfLifeService
                .getPinnacleOfLifeList(data, lifePathNumer);
        vipReport.setPinnacleOfLifeResponseDTOForReportList(pinnacleOfLifeList);

        return vipReport;
    }

    @Override
    public Page<NumerologyReportResponseDTO> getNumerologyReportsPage(Pageable pageable) {
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        UserResponseDtoForNumerologyReport userResponseDto = userConverter
                .convertEntityToUserForNumerologyReportResponseDTO(user);
        Long userId = user.getId();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                5,
                Sort.by(Sort.Direction.DESC, "createTime"));
        Page<DataNumerologyReport> dataNumerologyReportPage = dataNumerologyReportRepository.
                findDataNumerologyReportByUserId(userId, pageable);
        List<NumerologyReportResponseDTO> responseDTOs = dataNumerologyReportPage
                .getContent()
                .stream()
                .map(data -> calculateVipReport(data, userResponseDto))
                .collect(Collectors.toList());
        return new PageImpl<>(responseDTOs, pageable, dataNumerologyReportPage.getTotalElements());
    }

    private ResponseDTO createResponseDTO(HttpStatus status, String message, Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(status);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return responseDTO;
    }



}
