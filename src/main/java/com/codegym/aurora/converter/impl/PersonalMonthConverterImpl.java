package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.PersonalMonthConverter;
import com.codegym.aurora.entity.PersonalMonth;
import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;
import com.codegym.aurora.service.PersonalMonthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalMonthConverterImpl implements PersonalMonthConverter {
    private final PersonalMonthService personalMonthService;
    @Override
    public List<PersonalMonthResponseDTO> converEntityToResponeDtoList(List<PersonalMonth> personalMonthList) {
        List<PersonalMonthResponseDTO> personalMonthResponseDTOList = new ArrayList<>();
        for (PersonalMonth personalMonth : personalMonthList){
            PersonalMonthResponseDTO dto = personalMonthService.getPersonalMonthItem(personalMonth.getPersonalMonthNumber());
            personalMonthResponseDTOList.add(dto);

        }
        return personalMonthResponseDTOList;
    }
}
