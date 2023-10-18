package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.PersonalMonthConverter;
import com.codegym.aurora.entity.PersonalMonth;
import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.from_file.PersonalYearList;
import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDtoForReport;
import com.codegym.aurora.repository.PersonalMonthRepository;
import com.codegym.aurora.repository.PersonalYearRepository;
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
    private final PersonalYearRepository personalYearRepository;
    private final PersonalMonthService personalMonthService;
    private final PersonalMonthConverter personalMonthConverter;
    private final PersonalMonthRepository personalMonthRepository;

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
    public PersonalYearResponseDTO getPersonalYearItem(int number) {
        PersonalYearResponseDTO result = staticPersonalYearList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy năm cá nhân phù hợp"));
        return result;
    }

    @Override
    public int calculatePersonalYearFirst(int attitudeNumber) {
        int currentYear = NumeroloryUtils.reduceNumber(getCurrentYear());
        int personalYear = currentYear + attitudeNumber;
        return NumeroloryUtils.reduceNumber(personalYear);
    }

    @Override
    public int calculatePersonalYearSecond(int attitudeNumber) {
        int nextYear = NumeroloryUtils.reduceNumber(getCurrentYear()  + 1);
        int personalYearSecond = nextYear + attitudeNumber;
        return NumeroloryUtils.reduceNumber(personalYearSecond);
    }

    @Override
    public int calculatePersonalYearThird(int attitudeNumber) {
        int nextTwoYear = NumeroloryUtils.reduceNumber(getCurrentYear() + 2);
        int personalYearThird = nextTwoYear + attitudeNumber;
        return NumeroloryUtils.reduceNumber(personalYearThird);
    }

    @Override
    public List<PersonalYear> createPersonalYearEntity(int attitudeNumber) {
        int reducedCurrentYear = NumeroloryUtils.reduceNumber(getCurrentYear());
        List<PersonalYear> personalYearList = new ArrayList<>();
        List<PersonalMonth> personalMonthList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int personalYear = reducedCurrentYear + attitudeNumber + i;

            if (personalYear == 11 || personalYear == 22) {
                personalMonthList = personalMonthService
                        .createPersonalMonthEntityByPersonalYear(personalYear);
                personalYearList.add(new PersonalYear(personalYear, personalMonthList));

            } else {
                int reduceNumber = NumeroloryUtils.reduceNumber(personalYear);
                personalMonthList = personalMonthService
                        .createPersonalMonthEntityByPersonalYear(reduceNumber);
                personalYearList.add(new PersonalYear(reduceNumber, personalMonthList));
            }

        }
        personalMonthRepository.saveAll(personalMonthList);
        return personalYearRepository.saveAll(personalYearList);
    }

    @Override
    public List<PersonalYearResponseDtoForReport> createPersonalYearResponseDtoForReport(List<PersonalYear> personalYearList) {
        List<PersonalYearResponseDtoForReport> personalYearResponseDtoForReportList = new ArrayList<>();
        for (PersonalYear personalYear: personalYearList){

            List<PersonalMonthResponseDTO> personalMonthResponseDTOList = personalMonthConverter
                    .converEntityToResponeDtoList(personalYear.getPersonalMonthList());
            PersonalYearResponseDtoForReport personalYearResponseDto = new PersonalYearResponseDtoForReport();
            PersonalYearResponseDTO getResponseDtoDataInStatic = getPersonalYearItem(personalYear.getPersonalYearNumber());
            BeanUtils.copyProperties(getResponseDtoDataInStatic, personalYearResponseDto);
            personalYearResponseDto.setPersonalMonthResponseDTOList(personalMonthResponseDTOList);

            personalYearResponseDtoForReportList.add(personalYearResponseDto);

        }
        return personalYearResponseDtoForReportList;
    }

    private int getCurrentYear(){
        LocalDate date = LocalDate.now();
        return date.getYear();
    }
}
