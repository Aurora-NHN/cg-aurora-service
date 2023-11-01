package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequestDto {
    private Long id;

    private String name;

    private long price;

    private Integer weight;

    private int quantity;

    private String description;

    private int quantitySold;

    private String author;

    private String include;

    private String producer;

    private int height;

    private Boolean isDelete;

    private Boolean isActivated;

    @NotBlank
    private List<MultipartFile> productImageList = new ArrayList<>();

    private Long subCategoryId;
}
