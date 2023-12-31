package com.codegym.aurora.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@RequiredArgsConstructor

public class ProductInAdminResponseDTO {
    private Long id;

    private String name;

    private long price;

    private int weight;

    private int quantity;

    private String description;

    private int quantitySold;

    private String imageUrl;

    private String author;

    private String include;

    private String producer;

    private Date createDay;

    private Integer height;

    private Boolean isDelete;

    private Boolean isActivated;

    private List<String> productImageUrlList;

    private SubCategoryResponseDTO subCategory;
}
