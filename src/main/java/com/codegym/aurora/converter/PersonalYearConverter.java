package com.codegym.aurora.converter;

import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;

import java.util.List;

public interface PersonalYearConverter {
    List<PersonalYearResponseDTO> convertEntityToResponeDtoList(List<PersonalYear> personalYearList);
}
