package com.codegym.aurora.store_front.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeProductResponseDTO {

    private long id;

    private String productName;

    private String imageUrl;

    private long price;

    private int quantity;

    private int quantitySold;

}
