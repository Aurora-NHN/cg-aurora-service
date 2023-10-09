package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.LifePathResponeDTO;

public interface LifePathNumberService {
    LifePathResponeDTO calculateLifePathNumber(int day, int month, int year);


}
