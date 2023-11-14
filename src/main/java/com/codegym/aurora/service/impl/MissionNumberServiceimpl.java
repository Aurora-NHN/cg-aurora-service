package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.MissionNumberList;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.MissionNumberResponseDTO;
import com.codegym.aurora.service.MissionNumberService;
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
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MissionNumberServiceimpl implements MissionNumberService {
    private static final Map<Character, Integer> alphabetMap = NumeroloryConstants.alphabetMap;

    private  static List<MissionNumberResponseDTO> staticMissionNumberResponseDTOList = new ArrayList<>();
    static {
        try {
            staticMissionNumberResponseDTOList = loadStaticMissionNumberResponseDTOList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<MissionNumberResponseDTO> loadStaticMissionNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/mission-number.json").getInputStream()) {
            MissionNumberList missionNumberList = new ObjectMapper().readValue(inputStream, MissionNumberList.class);
            return missionNumberList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MissionNumberResponseDTO getMissionNumberResponseDTO(Integer missionNumber) {

        MissionNumberResponseDTO result = staticMissionNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == missionNumber)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số sứ mệnh phù hợp"));
        return result;
    }

    @Override
    public MissionNumberResponseDTO findMissionNumber(NumerologyReportRequestDTO requestDTO) {
        String fullName = NumeroloryUtils.removeAccent(requestDTO.getFullName());
        Integer missionNumber  = 0;
        String upperCaseName = fullName.toUpperCase();
        for (char letter : upperCaseName.toCharArray()) {
            if (alphabetMap.containsKey(letter)) {
                missionNumber += alphabetMap.get(letter);
            }
        }
        if (missionNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
                missionNumber == NumeroloryConstants.MASTER_NUMBER_22){
            return getMissionNumberResponseDTO(missionNumber);
        }

        while (missionNumber > 9) {
            missionNumber = reduceToSingleDigit(missionNumber);
        }
        return getMissionNumberResponseDTO(missionNumber);
    }

    @Override
    public MissionNumberResponseDTO findMissionNumber(DataNumerologyReport data) {
        String fullName = NumeroloryUtils.removeAccent(data.getFullName());
        Integer missionNumber  = 0;
        String upperCaseName = fullName.toUpperCase();
        for (char letter : upperCaseName.toCharArray()) {
            if (alphabetMap.containsKey(letter)) {
                missionNumber += alphabetMap.get(letter);
            }
        }
        if (missionNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
                missionNumber == NumeroloryConstants.MASTER_NUMBER_22){
            return getMissionNumberResponseDTO(missionNumber);
        }

        while (missionNumber > 9) {
            missionNumber = reduceToSingleDigit(missionNumber);
        }
        return getMissionNumberResponseDTO(missionNumber);
    }

    private int reduceToSingleDigit(Integer number) {
        if (number <= 9) {
            return number;
        }

        Integer sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return reduceToSingleDigit(sum);
    }
}
