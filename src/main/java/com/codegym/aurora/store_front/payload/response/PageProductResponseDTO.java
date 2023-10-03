package com.codegym.aurora.store_front.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageProductResponseDTO {
    private long id;

    private String name;

    private String imageUrl;

    private long price;

    private int quantity;

    private int quantitySold;
}
