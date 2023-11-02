package com.codegym.aurora.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.util.List;
@Data
@RequiredArgsConstructor
public class ProductResponseDTO {

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

    private boolean isDelete;

    private boolean isActivated;

    private List<ProductImageResponseDTO> productImageUrlList;


}
