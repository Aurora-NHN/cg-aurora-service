package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.NumerologyReportConverter;
import com.codegym.aurora.entity.NumerologyReport;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.FreeNumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.NumerologyReportResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.NumerologyReportRepository;
import com.codegym.aurora.repository.UserRepository;
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
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public ResponseDTO createNumerologyReportResponeDTO(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        user.setCount(user.getCount()-1);
        userRepository.save(user);
        NumerologyReport numerologyReport = numerologyReportConverter
                .convertRequestDtoToEntity(numerologyReportRequestDTO);
        NumerologyReportResponseDTO numerologyReportResponseDTO = numerologyReportConverter
                .convertEntityToResponseDTO(numerologyReport);

        //Nhớ lưu báo cáo
        numerologyReportRepository.save(numerologyReport);
        return createResponseDTO(
                HttpStatus.CREATED,
                "Create numerology report response DTO successfully!",
                numerologyReportResponseDTO);
    }

    @Override
    public ResponseDTO createFreeNumerologyReportResponeDTO(NumerologyReportRequestDTO numerologyReportRequestDTO) {

        NumerologyReport numerologyReport = numerologyReportConverter
                .convertRequestDtoToEntityForFreeNumber(numerologyReportRequestDTO);
        FreeNumerologyReportResponseDTO freeNumerologyReportResponseDTO = numerologyReportConverter
                .convertEntityToFreeNumerologyReportResponseDTO(numerologyReport);
        return createResponseDTO(
                HttpStatus.CREATED,
                "Create free numerology report response DTO successfully!",
                freeNumerologyReportResponseDTO);
    }

    @Override
    public int checkCount() {
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        return user.getCount();
    }

    @Override
    public ResponseDTO createFullNumerologyReportResponseDTO(NumerologyReportRequestDTO numerologyReportRequestDTO) {
        //tạo report rồi trả
        //đồng tời trừ
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        user.setCount(user.getCount()-1);
        userRepository.save(user);
        return null;
    }


    private ResponseDTO createResponseDTO(HttpStatus status, String message, Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(status);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return responseDTO;
    }
}
