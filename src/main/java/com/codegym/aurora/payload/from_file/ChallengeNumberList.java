package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.ChallengeNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ChallengeNumberList {
    private List<ChallengeNumberResponseDTO> items;
}
