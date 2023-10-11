package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequestDtoForCreate {

    @NotBlank
    @Size(max = 255)
    private String name;
    @NotNull
    private Long categoryId;
}