package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.AttitudeNumber;
import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;
import com.codegym.aurora.service.AttitudeNumberService;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AttitudeNumberServiceImpl implements AttitudeNumberService {
    private  static List<AttitudeNumberResponseDTO> staticAttitudeNumberResponseDTOList = new ArrayList<>();
    static {
        try {
            staticAttitudeNumberResponseDTOList = loadStaticAttitudeNumberList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<AttitudeNumberResponseDTO> loadStaticAttitudeNumberList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/attitude-number.json").getInputStream()) {
            AttitudeNumber attitudeNumber = new ObjectMapper().readValue(inputStream, AttitudeNumber.class);
            return attitudeNumber.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public AttitudeNumberResponseDTO getAttitudeNumber(int number) {
       AttitudeNumberResponseDTO result = staticAttitudeNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy chỉ số thái độ phù hợp"));
        return result;
    }
    @Override
    public int calculateAttitudeNumber(int day, int month) {
        int sum = day + month;
        int attitudeNumber = NumeroloryUtils.reduceNumber(sum);
        return getAttitudeNumber(attitudeNumber).getNumber();
    }

}
