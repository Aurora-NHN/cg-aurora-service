package com.codegym.aurora.payload.response;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDTO {

    private long id;

    private String name;

    private long price;

    private int weighT;

    private int quantity;

    private String description;

    private int quantitySold;

    private String imageUrl;

    private List<ProductImageResponseDTO> productImageUrlList = new ArrayList<>();


}