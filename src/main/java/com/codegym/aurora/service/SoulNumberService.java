package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.SoulNumberResponseDTO;

public interface SoulNumberService {
    SoulNumberResponseDTO getSoulNumberResponseDtoInStatic(int soulNumber);
    int calculateSoulNumber(String fullname);

}
