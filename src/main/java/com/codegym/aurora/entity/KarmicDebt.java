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
@Table(name = "KARMIC_DEBT", uniqueConstraints = @UniqueConstraint(columnNames = "INDICATORS"))

public class KarmicDebt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "INDICATORS")
    private int indicators;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_DELETED", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name = "IS_ACTIVATED", columnDefinition = "boolean default true")
    private boolean isActivated;

    @OneToMany(mappedBy = "karmicDebt", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<NumeroloryReport> numeroloryReportList = new ArrayList<>();
}
