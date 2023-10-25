package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.PersonalMonth;
import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.from_file.PersonalMonthList;
import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;
import com.codegym.aurora.repository.PersonalMonthRepository;
import com.codegym.aurora.repository.PersonalYearRepository;
import com.codegym.aurora.service.PersonalMonthService;
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
public class PersonalMonthServiceImpl implements PersonalMonthService {
    private final PersonalMonthRepository personalMonthRepository;
    private final PersonalYearRepository personalYearRepository;

    private static List<PersonalMonthResponseDTO> staticPersonalMonthList = new ArrayList<>();

    static {
        try {
            staticPersonalMonthList = loadStaticPersonalMonthList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<PersonalMonthResponseDTO> loadStaticPersonalMonthList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/personal-month.json").getInputStream()) {
            PersonalMonthList personalMonthList= new ObjectMapper().readValue(inputStream, PersonalMonthList.class);
            return personalMonthList.getItems();
        } catch (IOException error) {
            error.printStackTrace();
            throw error;
        }
    }
    @Override
    public PersonalMonthResponseDTO getPersonalMonthItem(int number) {
        PersonalMonthResponseDTO result = staticPersonalMonthList.stream()
                .filter(dto -> dto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tháng cá nhân phù hợp"));
        return result;
    }

    @Override
    public int calculatePersonalMonth(int personalYear, int month) {
        return NumeroloryUtils.reduceNumber(personalYear + NumeroloryUtils.reduceNumber(month));
    }

    @Override
    public List<PersonalMonth> createPersonalMonthEntityByPersonalYear(int personalYear) {
        List<PersonalMonth> personalMonths = new ArrayList<>();
        for (int month = 1; month <= 12; month++){
            int sumYearAndMonthReduce =  NumeroloryUtils.reduceNumber(personalYear + NumeroloryUtils.reduceNumber(month));
            PersonalMonth personalMonth = new PersonalMonth(month, sumYearAndMonthReduce);
            personalMonths.add(personalMonth);
        }
        return personalMonths;
    }

    @Override
    public void saveByPersonalYearId(Long id) {
        PersonalYear personalYear = personalYearRepository.findById(id).orElseThrow();
        List<PersonalMonth> personalMonths = createPersonalMonthEntityByPersonalYear(personalYear.getPersonalYearNumber());
        for (PersonalMonth item: personalMonths){
            item.setPersonalYear(personalYear);
            personalMonthRepository.save(item);
        }
    }

}
