package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.entity.ChallengeNumber;
import com.codegym.aurora.entity.LifePhase;
import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.entity.PinnacleOfLife;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.FreeNumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.ChallengeNumberRepository;
import com.codegym.aurora.repository.LifePhaseRepository;
import com.codegym.aurora.repository.NumerologyReportRepository;
import com.codegym.aurora.repository.PersonalYearRepository;
import com.codegym.aurora.repository.PinnacleOfLifeRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.NumerologyReportService;
import com.codegym.aurora.service.PersonalMonthService;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumerologyReportServiceImpl implements NumerologyReportService {
    private final NumerologyReportRepository numerologyReportRepository;
    private final NumerologyReportConverter numerologyReportConverter;
    private final UserService userService;
    private final UserRepository userRepository;
    private final LifePhaseRepository lifePhaseRepository;
    private final ChallengeNumberRepository challengeNumberRepository;
    @Override
    public ResponseDTO createNumerologyReportResponse(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        HttpStatus status;
        NumerologyReportResponseDTO numerologyReportResponseDTO = null;
        String message;

        if (numerologyReportRequestDTO.getVip()) {

            User user = userRepository.findByUsername(userService.getCurrentUsername());
            int count = user.getCount();

            if (user == null || count <= 0) {
                status = HttpStatus.PAYMENT_REQUIRED;
                message = "Vui lòng mua VIP để xem báo cáo với đầy đủ các chỉ số!";
            } else {
                count = count - 1;
                user.setCount(count);
                if (count == 0){
                    user.setVip(false);
                }
                userRepository.save(user);
                NumerologyReport numerologyReport = numerologyReportConverter
                        .convertRequestDtoToEntity(numerologyReportRequestDTO);
                numerologyReportResponseDTO = numerologyReportConverter
                        .convertEntityToResponseDTO(numerologyReport);
                numerologyReport.setDeleted(false);
                numerologyReport.setActivated(true);
                numerologyReportRepository.save(numerologyReport);
                ChallengeNumber challengeNumber = numerologyReport.getChallengeNumber();
                challengeNumber.setNumerologyReport(numerologyReport);
                challengeNumberRepository.save(challengeNumber);

                LifePhase lifePhase = numerologyReport.getLifePhase();
                lifePhase.setNumerologyReport(numerologyReport);
                lifePhaseRepository.save(lifePhase);

                status = HttpStatus.CREATED;
                message = "Create numerology report successfully!";
            }
            return createResponseDTO(status, message, numerologyReportResponseDTO);

        } else {
            NumerologyReport numerologyReport = numerologyReportConverter
                    .convertRequestDtoToEntityForFreeNumber(numerologyReportRequestDTO);
            numerologyReportResponseDTO = numerologyReportConverter
                    .convertEntityToNumerologyReportForFreeVersion(numerologyReport);
            return createResponseDTO(
                    HttpStatus.OK,
                    "Create free version for numerology report successfully!",
                    numerologyReportResponseDTO);
        }

    }

    @Override
    public int checkCount() {
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        return user.getCount();
    }

    private ResponseDTO createResponseDTO(HttpStatus status, String message, Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(status);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return responseDTO;
    }
}
