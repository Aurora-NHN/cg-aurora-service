package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PinnacleOfLifeResponseDTOForReport {

    private Integer number;
    private String name;
    private String title;
    private String description;
    private Integer age;
}
