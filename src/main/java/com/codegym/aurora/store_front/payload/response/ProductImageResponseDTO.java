package com.codegym.aurora.store_front.payload.response;

import com.codegym.aurora.store_front.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponseDTO {
    private long id;

    private String imageUrl;
}
