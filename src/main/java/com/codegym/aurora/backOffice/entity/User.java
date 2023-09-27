package com.codegym.aurora.backOffice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String role;

    @Column(name = "IS_VIP")
    private boolean isVip;

    @Column(name = "IS_DELETE")
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED")
    private boolean isActivated;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

}
