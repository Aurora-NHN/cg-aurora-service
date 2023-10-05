package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SubCategoryResponseDTO {

    private Long id;
    private String name;
    private boolean isDelete;
    private boolean isActivated;
}
