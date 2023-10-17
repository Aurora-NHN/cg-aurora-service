package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.from_file.PersonalYearList;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.repository.PersonalYearRepository;
import com.codegym.aurora.service.PersonalYearService;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
        if (personalYear== 11 || personalYear == 22){
            return personalYear;
        }
        return NumeroloryUtils.reduceNumber(personalYear);
    }

    @Override
    public int calculatePersonalYearSecond(int attitudeNumber) {
        int nextYear = NumeroloryUtils.reduceNumber(getCurrentYear()  + 1);
        int personalYearSecond = nextYear + attitudeNumber;
        if (personalYearSecond== 11 || personalYearSecond == 22){
            return personalYearSecond;
        }
        return NumeroloryUtils.reduceNumber(personalYearSecond);
    }

    @Override
    public int calculatePersonalYearThird(int attitudeNumber) {
        int nextTwoYear = NumeroloryUtils.reduceNumber(getCurrentYear() + 2);
        int personalYearThird = nextTwoYear + attitudeNumber;
        if (personalYearThird == 11 || personalYearThird == 22){
            return personalYearThird;
        }
        return NumeroloryUtils.reduceNumber(personalYearThird);
    }

    @Override
    public List<PersonalYear> createPersonalYearEntity(int attitudeNumber) {

        List<PersonalYear> personalYearList = new ArrayList<>();
        int calculatePersonalYearFirst = calculatePersonalYearFirst(attitudeNumber);
        int calculatePersonalPersonalYearSecond = calculatePersonalYearSecond(attitudeNumber);
        int calculatePersonalPersonalYearThird = calculatePersonalYearThird(attitudeNumber);
        personalYearList.add( new PersonalYear(calculatePersonalYearFirst));
        personalYearList.add( new PersonalYear(calculatePersonalPersonalYearSecond));
        personalYearList.add( new PersonalYear(calculatePersonalPersonalYearThird));

        return personalYearList;
    }
    private int getCurrentYear(){
        LocalDate date = LocalDate.now();
        return date.getYear();
    }
}
