package com.codegym.aurora.store_front.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SUB_CATEGORY")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "IS_DELETE", nullable = false)
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED", nullable = false)
    private boolean isActivated;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

}
