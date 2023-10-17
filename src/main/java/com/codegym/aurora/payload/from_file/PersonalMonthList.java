package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.PersonalMonthResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class PersonalMonthList {
    List<PersonalMonthResponseDTO> items;
}
