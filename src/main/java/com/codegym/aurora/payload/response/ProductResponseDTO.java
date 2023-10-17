package com.codegym.aurora.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class ProductResponseDTO {

    private long id;

    private String name;

    private long price;

    private int weight;

    private int quantitySold;

    private String imageUrl;

    private boolean isDelete;

    private int quantity;

    private boolean isActivated;

    private String description;

    private List<ProductImageResponseDTO> productImageUrlList;

}