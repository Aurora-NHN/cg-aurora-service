package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDtoForReport;

import java.util.List;

public interface PersonalYearService {

    PersonalYearResponseDTO getPersonalYearItem(Integer number);

    Integer calculatePersonalYear(Integer attitudeNumber,Integer year);
    List<PersonalYearResponseDtoForReport> createPersonalYearList(Integer attitudeNumber);



}
