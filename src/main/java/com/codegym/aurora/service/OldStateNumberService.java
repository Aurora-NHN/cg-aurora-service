package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.OldStateNumberResponseDTO;

public interface OldStateNumberService {
    OldStateNumberResponseDTO getOldStateNumber(int number);
    int calculateOldStateNumber(int year);
}
