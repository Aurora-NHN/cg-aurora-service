package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttitudeNumberResponseDTO {
    private int number;
    private String title;
    private String paragraph_1;
    private String paragraph_2;
}
