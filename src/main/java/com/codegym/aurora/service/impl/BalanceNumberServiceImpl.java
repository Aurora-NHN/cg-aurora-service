package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.BalanceNumberList;
import com.codegym.aurora.payload.from_file.SoulNumberList;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;
import com.codegym.aurora.payload.response.SoulNumberResponseDTO;
import com.codegym.aurora.service.BalanceNumberService;
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
public class BalanceNumberServiceImpl implements BalanceNumberService {
    private static final Map<Character, Integer> alphabetMap = NumeroloryConstants.alphabetMap;
    private static List<BalanceNumberResponseDTO> staticBalanceNumberResponseDTOList = new ArrayList<>();

    static {
        try {
            staticBalanceNumberResponseDTOList = loadStaticBalanceNumberResponseDTOList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<BalanceNumberResponseDTO> loadStaticBalanceNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/balance-number.json").getInputStream()) {
            BalanceNumberList balanceNumberList = new ObjectMapper().readValue(inputStream, BalanceNumberList.class);
            return balanceNumberList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public BalanceNumberResponseDTO getBalanceNumberResponseDtoInStatic(Integer balanceNumber) {
       BalanceNumberResponseDTO result = staticBalanceNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == balanceNumber)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số cân bằng phù hợp"));
        return result;
    }

    @Override
    public Integer calculateBalanceNumber(DataNumerologyReport data) {
        Integer balanceNumberSum = 0;
        String fullName = NumeroloryUtils.removeAccent(data.getFullName());
        String upperCaseName = fullName.toUpperCase();
        String[] namePhrases = upperCaseName.split(" ");

        for(String namePhrase : namePhrases) {
            char firstChar = namePhrase.charAt(0);
            if (alphabetMap.containsKey(firstChar)) {
                balanceNumberSum += alphabetMap.get(firstChar);
            }
        }

        return NumeroloryUtils.reduceToSingleDigit(balanceNumberSum);
    }

    @Override
    public BalanceNumberResponseDTO findBalanceNumber(DataNumerologyReport data) {
        Integer balanceNumer = calculateBalanceNumber(data);
        return getBalanceNumberResponseDtoInStatic(balanceNumer);
    }


}
