package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageProductResponseDTO {
    private long id;

    private String name;

    private long price;

    private int weight;

    private int quantity;

    private String description;

    private int quantitySold;

    private String imageUrl;

    private List<ProductImageResponseDTO> productImageUrlList = new ArrayList<>();
}
