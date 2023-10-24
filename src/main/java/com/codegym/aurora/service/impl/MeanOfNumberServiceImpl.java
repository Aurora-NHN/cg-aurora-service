package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.MeanOfNumberList;
import com.codegym.aurora.payload.response.MeanOfNumberResponseDTO;
import com.codegym.aurora.service.MeanOfNumberService;
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
public class MeanOfNumberServiceImpl implements MeanOfNumberService {
    private static List<MeanOfNumberResponseDTO> staticMeanAllNumberResponseDto = new ArrayList<>();
    static {
        try {
            staticMeanAllNumberResponseDto = loadStaticMeanAllNumberResponseDtoList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static List<MeanOfNumberResponseDTO> loadStaticMeanAllNumberResponseDtoList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/mean-all-number.json").getInputStream()) {
            MeanOfNumberList meanOfAllNumberList = new ObjectMapper().readValue(inputStream, MeanOfNumberList.class);
            return meanOfAllNumberList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MeanOfNumberResponseDTO getMeanOfNumberResponseDto(String name) {
        MeanOfNumberResponseDTO result = staticMeanAllNumberResponseDto.stream()
                .filter(dto -> dto.getNameOfNumber().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tên của chỉ số"));
        return result;
    }

    @Override
    public List<MeanOfNumberResponseDTO> getMeanOfAllNumberResponseDto() {
        List<MeanOfNumberResponseDTO> meanOfNumberResponseDTOList = staticMeanAllNumberResponseDto;
        return meanOfNumberResponseDTOList;
    }
}
