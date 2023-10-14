package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.DayOfBirthNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class DayOfBirthNumber {
    private List<DayOfBirthNumberResponseDTO> items;
}
