package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.MatureStateNumberResponseDTO;

public interface MatureStateNumberService {
   MatureStateNumberResponseDTO getMatureStateNumber(int number);

   int calculateMatureStateNumber(int day);
}
