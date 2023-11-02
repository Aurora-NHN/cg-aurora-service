package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.ZodiacResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class Zodiac {

    String name;

    List<ZodiacResponseDTO> items;
}
