package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.NumerologyReportRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.LifePathNumberService;
import com.codegym.aurora.service.NumerologyReportService;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NumerologyReportServiceImpl implements NumerologyReportService {
    private final NumerologyReportRepository numerologyReportRepository;
    private final NumerologyReportConverter numerologyReportConverter;
    private final LifePathNumberService lifePathNumberService;
//    private final DayOfBirthNumberService dayOfBirthNumberService;
    private final UserService userService;
    private final UserRepository userRepository;


    @Override
    public ResponseDTO createNumerologyReportResponeDTO(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        NumerologyReport numerologyReport = numerologyReportConverter.convertRequestDtoToEntity(numerologyReportRequestDTO);
        NumerologyReportResponseDTO numerologyReportResponseDTO = numerologyReportConverter
                .convertEntityToResponseDTO(numerologyReport);
        return createResponseDTO(HttpStatus.OK, "Get NumerologyReportResponeDTO successfully!", numerologyReportResponseDTO);
    }

    @Override
    public ResponseDTO saveNumerologyReport(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        NumerologyReport numerologyReport = numerologyReportConverter.convertRequestDtoToEntity(numerologyReportRequestDTO);
        numerologyReportRepository.save(numerologyReport);
        return createResponseDTO(HttpStatus.CREATED, "Create Numerology successfully!", numerologyReport);
    }

    private ResponseDTO createResponseDTO(HttpStatus status, String message, Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(status);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return responseDTO;
    }
}
