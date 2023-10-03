package com.codegym.aurora.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String role;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "IS_VIP")
    private boolean isVip;

    @Column(name = "IS_DELETE")
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED")
    private boolean isActivated;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

}
