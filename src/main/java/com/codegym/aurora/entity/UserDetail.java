package com.codegym.aurora.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_DETAIL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

}