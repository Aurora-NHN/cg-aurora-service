package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.YoungStateNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class YoungStateNumber {
    List<YoungStateNumberResponseDTO> items;
}
