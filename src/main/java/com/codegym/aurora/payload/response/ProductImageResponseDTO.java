package com.codegym.aurora.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponseDTO {
    private long id;

    private String imageUrl;
}
