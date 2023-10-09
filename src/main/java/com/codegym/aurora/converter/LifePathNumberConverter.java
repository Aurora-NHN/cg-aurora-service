package com.codegym.aurora.converter;

import com.codegym.aurora.entity.LifePathNumber;
import com.codegym.aurora.payload.response.LifePathResponeDTO;

public interface LifePathNumberConverter {
    LifePathResponeDTO convertEntitytoDTO(LifePathNumber lifePathNumber);
}
