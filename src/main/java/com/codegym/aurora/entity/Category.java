package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IS_DELETE")
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED")
    private boolean isActivated;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<SubCategory> subCategoryList = new ArrayList<>();

}
