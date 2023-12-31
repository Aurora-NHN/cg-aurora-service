package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.YoungStateNumber;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.YoungStateNumberResponseDTO;
import com.codegym.aurora.service.YoungStateNumberService;
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
public class YoungNumberServiceImpl implements YoungStateNumberService {
    private  static List<YoungStateNumberResponseDTO> staticYoungStateNumberResponseDTOList = new ArrayList<>();
    static {
        try {
            staticYoungStateNumberResponseDTOList = loadStaticYoungStateNumberResponseDTOList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<YoungStateNumberResponseDTO> loadStaticYoungStateNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/life-phase-young-state-number.json").getInputStream()) {
            YoungStateNumber youngStateNumber = new ObjectMapper().readValue(inputStream, YoungStateNumber.class);
            return youngStateNumber.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public YoungStateNumberResponseDTO getYoungStateNumber(Integer number) {
        YoungStateNumberResponseDTO result = staticYoungStateNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy chỉ số giai đoạn tiền vận phù hợp"));
        return result;
    }

    @Override
    public YoungStateNumberResponseDTO findYoungStateNumber(DataNumerologyReport data) {
        Integer youngStateNumber = NumeroloryUtils.calculateDigitSum(data.getMonthOfBirth());
        if (youngStateNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
            youngStateNumber == NumeroloryConstants.MASTER_NUMBER_22){
            return  getYoungStateNumber(youngStateNumber);
        }
        return getYoungStateNumber(NumeroloryUtils.reduceNumber(youngStateNumber));

    }


}