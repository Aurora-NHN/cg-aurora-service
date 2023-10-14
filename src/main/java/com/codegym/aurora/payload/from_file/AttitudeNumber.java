package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.AttitudeNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class AttitudeNumber {
    private List<AttitudeNumberResponseDTO> items;


}
