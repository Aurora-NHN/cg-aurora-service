package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.MeanOfNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class MeanOfNumberList {
    private List<MeanOfNumberResponseDTO> items;
}
