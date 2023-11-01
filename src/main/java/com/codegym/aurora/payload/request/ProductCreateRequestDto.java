package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequestDto {

    private String name;

    private long price;

    private int weight;

    private int quantity;

    private String description;

    private String author;

    private String include;

    private String producer;

    private Integer height;

    private List<MultipartFile> productImageList = new ArrayList<>();

    private Long subCategoryId;

}
