package com.codegym.aurora.converter;

import com.codegym.aurora.entity.PersonalMonth;
import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;

import java.util.List;

public interface PersonalMonthConverter {
    List<PersonalMonthResponseDTO> converEntityToResponeDtoList(List<PersonalMonth> personalMonthList);

}
