package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String role;

    @Column(name = "count")
    private int count;

    @Column(name = "total_count")
    private int totalCount;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "is_activated")
    private boolean isActivated;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private UserDetail userDetail;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<HistoryPayment> historyPayments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<DataNumerologyReport> dataNumerologyReportList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Cart carts;
}