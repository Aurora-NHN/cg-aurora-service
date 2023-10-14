package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.OldStateNumber;
import com.codegym.aurora.payload.response.OldStateNumberResponseDTO;
import com.codegym.aurora.service.OldStateNumberService;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OldStateNumberServiceImpl implements OldStateNumberService {

    private  static List<OldStateNumberResponseDTO> staticOldStateNumberResponseDTOList = new ArrayList<>();
    static {
        try {
            staticOldStateNumberResponseDTOList = loadStaticOldStateNumberResponseDTOList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<OldStateNumberResponseDTO> loadStaticOldStateNumberResponseDTOList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/life-phase-old-state-number.json").getInputStream()) {
            OldStateNumber oldStateNumber = new ObjectMapper().readValue(inputStream, OldStateNumber.class);
            return oldStateNumber.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public OldStateNumberResponseDTO getOldStateNumber(int number) {
        OldStateNumberResponseDTO result = staticOldStateNumberResponseDTOList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy chỉ số hậu vận phù hợp"));
        return result;
    }

    @Override
    public int calculateOldStateNumber(int year) {
        int sumYear = NumeroloryUtils.calculateDigitSum(year);
        int oldStateNumber = calculateReducedNumber(sumYear);
        return getOldStateNumber(oldStateNumber).getNumber();
    }
    private int calculateReducedNumber(int number) {
        while (number > 9) {
            number = NumeroloryUtils.calculateDigitSum(number);
        }
        return number;
    }
}
