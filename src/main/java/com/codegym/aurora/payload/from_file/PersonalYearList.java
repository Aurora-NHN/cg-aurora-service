package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.PersonalYearResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class PersonalYearList {
        List<PersonalYearResponseDTO> items;
}
