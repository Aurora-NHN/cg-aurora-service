package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.SoulNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class SoulNumberList {
    List<SoulNumberResponseDTO> items;
}
