package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private long id;

    private String name;

    private List<SubCategoryResponseDTO> subCategoryList = new ArrayList<>();
}