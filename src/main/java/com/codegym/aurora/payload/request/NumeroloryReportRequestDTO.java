package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumeroloryReportRequestDTO {

    private Long id;

    @NotBlank
    @Size(min = 5)
    private String fullName;

    @NotNull
    private int dayOfBirth;

    @NotNull
    private int monthOfBirth;

    @NotNull
    private int yearOfBirth;
}
