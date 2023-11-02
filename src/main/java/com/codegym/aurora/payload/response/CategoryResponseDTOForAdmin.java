package com.codegym.aurora.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CategoryResponseDTOForAdmin {

    private Long id;
    private String name;
    private boolean isDeleted;
    private boolean active;
    private String description;
    private String thumbUrl;
    private List<String> subCategories;
}
