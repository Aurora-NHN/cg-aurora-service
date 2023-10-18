package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.MissionNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class MissionNumberList {
    List<MissionNumberResponseDTO> items;
}
