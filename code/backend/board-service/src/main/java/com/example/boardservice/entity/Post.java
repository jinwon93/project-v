package com.example.boardservice.entity;


import com.example.boardservice.dto.post.request.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "post" , cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();



    @Builder
    public Post(String category, Long clubId, Long writerId, String title, String content, Vote vote, List<Comment> comments) {
        this.category = category;
        this.clubId = clubId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.vote = vote;
        this.comments = comments;
    }

    public void  update(PostUpdateRequestDto postUpdateRequestDto) {

    }
}
