package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;

import java.util.List;

public interface PersonalMonthService {
    PersonalMonthResponseDTO getPersonalMonthItem(Integer number);
    Integer calculatePersonalMonth(Integer personalYear, Integer month);
    List<PersonalMonthResponseDTO> createPersonalMonth(Integer personalYear);
}
