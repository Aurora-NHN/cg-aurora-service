package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.LifePathResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class LifePathNumber {
    private List<LifePathResponseDTO> items;
}
