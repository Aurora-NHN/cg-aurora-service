package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalYearResponseDtoForReport {
    private int number;
    private String name;
    private String title;
    private String description;
    private List<PersonalMonthResponseDTO> personalMonthResponseDTOList;
}
