package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Boolean publish;
    private String content;
    private String description;
    private String tag;
    @Column(name = "main_image")
    private String mainImageFilename;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_modify")
    private LocalDateTime lastModify;
    @OneToMany(mappedBy = "blog")
    @Cascade({CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DELETE})
    private List<BlogContentImage> blogContentImages;
}
