package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;

public interface AttitudeNumberService {
    AttitudeNumberResponseDTO getAttitudeNumber(int number);

    int calculateAttitudeNumber(int day, int month);
}
