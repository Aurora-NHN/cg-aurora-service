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
public class SubCategoryRequestDTO {

    private Long id;
    @NotBlank
    @Size(max = 255)
    private String name;
    private boolean isDelete;
    private boolean isActivated;
    @NotNull
    private Long categoryId;
}
