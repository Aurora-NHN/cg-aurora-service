package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.SoulNumberList;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.SoulNumberResponseDTO;
import com.codegym.aurora.service.SoulNumberService;
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
public class SoulNumberServiceImpl implements SoulNumberService {
    private static final Map<Character, Integer> vowelLettersMap = NumeroloryConstants.vowelLettersMap;
    private static List<SoulNumberResponseDTO> staticSoulNumberResponseDTOList = new ArrayList<>();

    static {
        try {
            staticSoulNumberResponseDTOList = loadStaticSoulNumberResponseDTOList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<SoulNumberResponseDTO> loadStaticSoulNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/soul-number.json").getInputStream()) {
            SoulNumberList soulNumberList = new ObjectMapper().readValue(inputStream, SoulNumberList.class);
            return soulNumberList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public SoulNumberResponseDTO getSoulNumberResponseDtoInStatic(Integer soulNumber) {
        SoulNumberResponseDTO result = staticSoulNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == soulNumber)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số linh hồn phù hợp"));
        return result;
    }

    @Override
    public Integer calculateSoulNumber(String fullName) {
        Integer soulNumber = 0;
        String upperCaseName = fullName.toUpperCase();
        String[] namePhrases = upperCaseName.split(" ");

        for (String namePhrase : namePhrases) {
            char[] chars = namePhrase.toCharArray();
            if (chars.length == 1 && chars[0] == 'Y') {
                soulNumber += 7;
                continue;
            }
            for (int i = 0; i < chars.length; i++) {
                char currentChar = chars[i];

                if (vowelLettersMap.containsKey(currentChar)) {
                    soulNumber += vowelLettersMap.get(currentChar);
                    continue;
                }

                if (currentChar == 'Y') {
                    boolean validFirstY = (i == 0) && !vowelLettersMap.containsKey(chars[i + 1]);
                    boolean validLastY = (i == chars.length - 1) && !vowelLettersMap.containsKey(chars[i - 1]);
                    boolean validMiddleY = i != 0
                            && i != (chars.length - 1)
                            && vowelLettersMap.containsKey(chars[i - 1])
                            && vowelLettersMap.containsKey(chars[i + 1]);

                    if (validFirstY || validMiddleY || validLastY) {
                        soulNumber += 7;
                    }
                }

            }
        }
        Integer reduceSoulNumber = NumeroloryUtils.calculateDigitSum(soulNumber);
        if (reduceSoulNumber == NumeroloryConstants.MASTER_NUMBER_11 ||
                reduceSoulNumber == NumeroloryConstants.MASTER_NUMBER_22){
            return reduceSoulNumber;
        }
        return NumeroloryUtils.reduceNumber(reduceSoulNumber);
    }

    @Override
    public SoulNumberResponseDTO findSoulNumber(DataNumerologyReport data) {
        String fullName = NumeroloryUtils.removeAccent(data.getFullName());
        Integer soulNumer = calculateSoulNumber(fullName);
        return getSoulNumberResponseDtoInStatic(soulNumer);
    }

}
