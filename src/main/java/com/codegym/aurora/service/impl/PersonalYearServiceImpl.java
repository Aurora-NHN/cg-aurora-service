package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.from_file.PersonalYearList;
import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDtoForReport;
import com.codegym.aurora.service.PersonalMonthService;
import com.codegym.aurora.service.PersonalYearService;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PersonalYearServiceImpl implements PersonalYearService {
    private final PersonalMonthService personalMonthService;

    private static List<PersonalYearResponseDTO> staticPersonalYearList = new ArrayList<>();

    static {
        try {
            staticPersonalYearList = loadStaticPersonalYearList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<PersonalYearResponseDTO> loadStaticPersonalYearList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/personal-year.json").getInputStream()) {
            PersonalYearList personalYearList = new ObjectMapper().readValue(inputStream, PersonalYearList.class);
            return personalYearList.getItems();
        } catch (IOException error) {
            error.printStackTrace();
            throw error;
        }
    }
    @Override
    public PersonalYearResponseDTO getPersonalYearItem(Integer number) {
        PersonalYearResponseDTO result = staticPersonalYearList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy năm cá nhân phù hợp"));
        return result;
    }

    @Override
    public Integer calculatePersonalYear(Integer attitudeNumber, Integer year) {
        Integer personalYear = year + attitudeNumber;
        return NumeroloryUtils.reduceNumber(personalYear);
    }
    @Override
    public List<PersonalYearResponseDtoForReport> createPersonalYearList(Integer attitudeNumber) {
        List<PersonalYearResponseDtoForReport> personalYearList = new ArrayList<>();

        for (int index = 0; index < 3; index++){
            Integer currentYear = NumeroloryUtils.reduceNumber(getCurrentYear() + index);
            Integer personalYearNumber = calculatePersonalYear(attitudeNumber, currentYear);

            PersonalYearResponseDTO personalYear = getPersonalYearItem(personalYearNumber);
            PersonalYearResponseDtoForReport personalYearForReport = new PersonalYearResponseDtoForReport();
            BeanUtils.copyProperties(personalYear, personalYearForReport);

            List<PersonalMonthResponseDTO> personalMonthList = personalMonthService
                    .createPersonalMonth(personalYearNumber);
            personalYearForReport.setPersonalMonthResponseDTOList(personalMonthList);
            personalYearList.add(personalYearForReport);
        }
        return personalYearList;
    }

    private int getCurrentYear(){
        LocalDate date = LocalDate.now();
        return date.getYear();
    }
}
