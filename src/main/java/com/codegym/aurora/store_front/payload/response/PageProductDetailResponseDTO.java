package com.codegym.aurora.store_front.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageProductDetailResponseDTO {

    private long id;

    private String productName;

    private long price;

    private int weight;

    private int quantity;

    private String description;

    private int quantitySold;

}
