package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResponseDTO {
    private Long id;

    private String name;

    private long price;

    private int weight;

    private int quantity;

    private String description;

    private Integer height;

    private int quantitySold;

    private String author;

    private String include;

    private String producer;

    private LocalDate createDay;

    private boolean isDelete;

    private boolean isActivated;

    private String imageUrl;

    private String mainImageUrl;

    private List<String> productImageUrlList;

    private SubCategoryResponseDTO subCategoryResponseDTO;
}
