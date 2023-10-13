package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.PersonalYearConverter;
import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.service.PersonalYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalYearConverterImp implements PersonalYearConverter {
    private final PersonalYearService personalYearService;
    @Override
    public List<PersonalYearResponseDTO> convertEntityToResponeDtoList(List<PersonalYear> personalYearList) {
        List<PersonalYearResponseDTO> personalYearResponseDTOList = new ArrayList<>();

        PersonalYearResponseDTO firstYear = personalYearService
                .getPersonalYearItem(personalYearList.get(0).getPersonalYearNumber());
        PersonalYearResponseDTO secondYear = personalYearService
                .getPersonalYearItem(personalYearList.get(1).getPersonalYearNumber());
        PersonalYearResponseDTO thirdYear = personalYearService
                .getPersonalYearItem(personalYearList.get(2).getPersonalYearNumber());
        personalYearResponseDTOList.add(firstYear);
        personalYearResponseDTOList.add(secondYear);
        personalYearResponseDTOList.add(thirdYear);
        return personalYearResponseDTOList;
    }
}
