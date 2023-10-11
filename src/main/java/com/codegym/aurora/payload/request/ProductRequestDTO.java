package com.codegym.aurora.payload.request;

import com.codegym.aurora.payload.response.ProductImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private long id;

    private long price;

    private int weighT;

}
