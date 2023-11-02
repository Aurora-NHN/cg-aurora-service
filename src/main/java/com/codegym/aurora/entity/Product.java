package com.codegym.aurora.entity;

import lombok.*;

import javax.persistence.*;
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
    private long id;

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

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "INCLUDE", nullable = false)
    private String include;

    @Column(name = "PRODUCER", nullable = false)
    private String producer;

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

    @OneToMany(mappedBy = "product")
    private List<CartLine> cartLineList = new ArrayList<>();


}
