package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.MatureStateNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class MatureStateNumber {
    private List<MatureStateNumberResponseDTO> items;
}
