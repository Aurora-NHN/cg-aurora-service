package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.MiddleAgedNumberList;
import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;
import com.codegym.aurora.service.MiddleAgedNumberService;
import com.codegym.aurora.util.NumeroloryConstants;
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
public class MiddleAgedNumberServiceImpl implements MiddleAgedNumberService {
    private  static List<MiddleAgedNumberResponseDto> staticMiddleAgedNumberList = new ArrayList<>();
    static {
        try {
            staticMiddleAgedNumberList = loadStaticMiddleAgedNumberResponseDTOList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<MiddleAgedNumberResponseDto> loadStaticMiddleAgedNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/middle-aged-number.json").getInputStream()) {
            MiddleAgedNumberList middleAgedNumberList = new ObjectMapper().readValue(inputStream, MiddleAgedNumberList.class);
            return middleAgedNumberList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public MiddleAgedNumberResponseDto getMiddleAgedNumberResponseDto(Integer middleAgedNumber) {
        return staticMiddleAgedNumberList.stream()
                .filter(dto -> dto.getNumber() == middleAgedNumber)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số trung niên phù hợp"));
    }

    @Override
    public Integer calculateMiddleAgedNumber(Integer missionNumber, Integer lifePathNumber) {
        Integer sum = missionNumber + lifePathNumber;

        if (sum == NumeroloryConstants.MASTER_NUMBER_11 ||
                sum == NumeroloryConstants.MASTER_NUMBER_22 ||
                sum == NumeroloryConstants.MASTER_NUMBER_33){
            return sum;
        }
        return NumeroloryUtils.reduceNumber(sum);
    }



    @Override
    public MiddleAgedNumberResponseDto findMiddleAgeNumber(Integer missionNumber, Integer lifePathNumber) {
        Integer  middleAge = calculateMiddleAgedNumber(missionNumber, lifePathNumber);
        return getMiddleAgedNumberResponseDto(middleAge);
    }
}
