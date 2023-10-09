package com.codegym.aurora.converter;

import com.codegym.aurora.entity.DayOfBirthNumber;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponeDTO;

public interface DayOfBirthNumberConvert {
    DayOfBirthNumberResponeDTO convertEntityToResponeDTO(DayOfBirthNumber dayOfBirthNumber);
}
