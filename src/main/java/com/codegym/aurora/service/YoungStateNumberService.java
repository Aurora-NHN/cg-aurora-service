package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.YoungStateNumberResponseDTO;

public interface YoungStateNumberService {
    YoungStateNumberResponseDTO getYoungStateNumber(int number);

    int calculateYoungStateNumber(int month);
}

