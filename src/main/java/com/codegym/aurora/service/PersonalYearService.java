package com.codegym.aurora.service;

import com.codegym.aurora.entity.PersonalYear;
import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import com.codegym.aurora.payload.response.PersonalYearResponseDtoForReport;

import java.util.List;

public interface PersonalYearService {

    PersonalYearResponseDTO getPersonalYearItem(int number);
    int calculatePersonalYearFirst(int attitudeNumber);
    int calculatePersonalYearSecond(int attitudeNumber);
    int calculatePersonalYearThird(int attitudeNumber);

    List<PersonalYear> createPersonalYearEntity(int attitudeNumber);

    List<PersonalYearResponseDtoForReport> createPersonalYearResponseDtoForReport(List<PersonalYear> personalYearList);
}
