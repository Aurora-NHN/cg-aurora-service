package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.LifePathResponseDTO;

public interface LifePathNumberService {

    LifePathResponseDTO getLifePathNumber(int number);
    int calculateLifePathNumber(int day, int month, int year);
}
