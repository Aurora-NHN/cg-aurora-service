package com.codegym.aurora.store_front.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String name;

    @Column(name = "IS_DELETE", nullable = false)
    private boolean idDelete;

    @Column(name = "IS_ACTIVATED", nullable = false)
    private boolean isActivated;

    @OneToMany(mappedBy = "SUB_CATEGORY", cascade = CascadeType.ALL)
    private List<SubCategory> subCategoryList = new ArrayList<>();

}
