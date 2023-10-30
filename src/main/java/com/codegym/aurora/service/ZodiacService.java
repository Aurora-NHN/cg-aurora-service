package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.ZodiacResponseDTO;

import java.io.IOException;

public interface ZodiacService {

    ZodiacResponseDTO getZodiacSigns(String name) throws IOException;
}
