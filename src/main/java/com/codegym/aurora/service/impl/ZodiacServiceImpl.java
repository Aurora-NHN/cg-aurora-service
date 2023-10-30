package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.response.ZodiacResponseDTO;
import com.codegym.aurora.service.ZodiacService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ZodiacServiceImpl implements ZodiacService {

    @Override
    public ZodiacResponseDTO getZodiacSigns(String name) throws IOException {
        try (InputStream inputStream = new ClassPathResource(name).getInputStream()) {
            ZodiacResponseDTO zodiacResponseDTO = new ObjectMapper().readValue(inputStream, ZodiacResponseDTO.class);
            return zodiacResponseDTO;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
