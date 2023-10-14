package com.codegym.aurora.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CategoryResponseDTOForAdmin {

    private Long id;
    private String name;
    private boolean isDeleted;
    private boolean isActivated;

}
