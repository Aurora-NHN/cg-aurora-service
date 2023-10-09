package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.DayOfBirthNumberConvert;
import com.codegym.aurora.entity.DayOfBirthNumber;
import com.codegym.aurora.payload.response.DayOfBirthNumberResponeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DayOfBirthNumberConvertImpl implements DayOfBirthNumberConvert {
    @Override
    public DayOfBirthNumberResponeDTO convertEntityToResponeDTO(DayOfBirthNumber dayOfBirthNumber) {
        DayOfBirthNumberResponeDTO dayOfBirthNumberResponeDTO = new DayOfBirthNumberResponeDTO();
        BeanUtils.copyProperties(dayOfBirthNumber, dayOfBirthNumberResponeDTO);
        return dayOfBirthNumberResponeDTO;
    }
}
