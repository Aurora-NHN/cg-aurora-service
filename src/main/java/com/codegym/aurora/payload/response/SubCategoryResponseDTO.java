package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SubCategoryResponseDTO {
    private Long id;
    private String name;
    private Boolean isDelete;
    private Boolean active;
    private String description;
    private String thumbUrl;
    private String categoryName;
    private Long categoryId;
    private Long productTypeCount;
}
