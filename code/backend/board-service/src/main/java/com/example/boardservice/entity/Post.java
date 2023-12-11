package com.example.boardservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private Long clubId;

    private Long writerId;

    private String title;

    private String content;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE , orphanRemoval = true)
    private Vote vote;
}
