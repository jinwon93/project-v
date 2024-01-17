package com.example.boardservice.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.awt.*;

@Entity
@Getter

public class Comment  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long clubId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private Long writerId;

    private String content;

    private String commentStep;

    private int commentOrder;


    @Builder
    public Comment(Long id, Long clubId, Post post, Long writerId, String content, String commentStep, int commentOrder) {
        this.id = id;
        this.clubId = clubId;
        this.post = post;
        this.writerId = writerId;
        this.content = content;
        this.commentStep = commentStep;
        this.commentOrder = commentOrder;
    }
}
