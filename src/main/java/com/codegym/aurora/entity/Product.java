package com.codegym.aurora.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private long price;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "QUANTITY_SOLD")
    private int quantitySold;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "INCLUDE")
    private String include;

    @Column(name = "PRODUCER", nullable = false)
    private String producer;

    @Column(name = "create_day")
    private LocalDate createDay;

    @Column(name = "height")
    private Integer height;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "IS_DELETE", columnDefinition = "boolean default false")
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED", columnDefinition = "boolean default true")
    private boolean isActivated;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ProductImage> productImageUrlList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SUB_CATEGORY_ID",referencedColumnName = "ID")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE})
    private  List<OrderDetail> orderDetailList= new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<CartLine> cartLineList = new ArrayList<>();


}
