package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.LifePathNumberConverter;
import com.codegym.aurora.entity.LifePathNumber;
import com.codegym.aurora.payload.response.LifePathResponeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LifePathNumberConvertImpl implements LifePathNumberConverter {

    @Override
    public LifePathResponeDTO convertEntitytoDTO(LifePathNumber lifePathNumber) {
        LifePathResponeDTO lifePathResponeDTO = new LifePathResponeDTO();
        BeanUtils.copyProperties(lifePathNumber, lifePathResponeDTO);
        return lifePathResponeDTO;
    }
}
