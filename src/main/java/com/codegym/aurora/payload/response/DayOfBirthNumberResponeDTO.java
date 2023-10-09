package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOfBirthNumberResponeDTO {
    private Integer id;
    private int indicators;
    private String description;
}
