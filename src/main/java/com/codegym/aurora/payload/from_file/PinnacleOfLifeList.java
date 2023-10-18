package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class PinnacleOfLifeList {
    List<PinnacleOfLifeResponseDTO> items;
}
