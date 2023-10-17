package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.OldStateNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class OldStateNumber {
    private List<OldStateNumberResponseDTO> items;
}
