package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.MatureStateNumber;
import com.codegym.aurora.payload.response.MatureStateNumberResponseDTO;
import com.codegym.aurora.service.MatureStateNumberService;
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
public class MatureStateNumberServiceImpl implements MatureStateNumberService {
    private  static List<MatureStateNumberResponseDTO> staticMatureStateNumberResponseDTOList = new ArrayList<>();
    static {
        try {
            staticMatureStateNumberResponseDTOList = loadStaticMatureStateNumberResponseDTOList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<MatureStateNumberResponseDTO> loadStaticMatureStateNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/life-phase-mature-state-number.json")
                .getInputStream()) {
            MatureStateNumber matureStateNumber = new ObjectMapper().readValue(inputStream, MatureStateNumber.class);
            return matureStateNumber.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MatureStateNumberResponseDTO getMatureStateNumber(Integer number) {
        MatureStateNumberResponseDTO result = staticMatureStateNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy chỉ số trưởng thành phù hợp"));
        return result;
    }

    @Override
    public MatureStateNumberResponseDTO findMatureStateNumer(DataNumerologyReport data) {
        Integer matureStateNumber = NumeroloryUtils.calculateDigitSum(data.getDayOfBirth());
        if (matureStateNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
            matureStateNumber == NumeroloryConstants.MASTER_NUMBER_22){
            return getMatureStateNumber(matureStateNumber);
        }
        return getMatureStateNumber(NumeroloryUtils.reduceNumber(matureStateNumber));
    }
}
