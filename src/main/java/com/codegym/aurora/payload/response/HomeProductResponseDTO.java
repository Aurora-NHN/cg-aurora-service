package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeProductResponseDTO {

    private long id;

    private String name;

    private String imageUrl;

    private long price;

    private int quantity;

    private int quantitySold;

}
