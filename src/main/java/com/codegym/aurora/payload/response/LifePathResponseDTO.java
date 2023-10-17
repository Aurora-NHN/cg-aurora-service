package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LifePathResponseDTO {
    private int number;
    private String overview;
    private String title;
    private String paragraph_1;
    private String paragraph_2;

}
