package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LifePathResponeDTO {
    private Integer id;
    private int indicators;
    private String overview;
    private String nature;
    private String hobbies;
    private String careerDevelopmentOrientation;
    private String job;
    private String finance;
    private String love;
    private String health;
    private String karma;
    private String selfSuggestion;
    private String description;
}
