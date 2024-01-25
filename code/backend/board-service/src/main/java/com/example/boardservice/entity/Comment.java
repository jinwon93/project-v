package com.example.boardservice.entity;


import com.example.boardservice.dto.comment.request.CommentUpdateRequestDto;
import com.example.boardservice.dto.comment.response.CommentResponseDto;
import com.example.boardservice.etc.HtmlSanitizerUtil;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long clubId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private Long writerId;

    private String content;

    private int commentStep;

    private int commentOrder;

    private int commentGroup;

    public void update(CommentUpdateRequestDto commentUpdateRequestDto){
        this.content = HtmlSanitizerUtil.sanitize(commentUpdateRequestDto.getContent());
    }



    public CommentResponseDto toResponseDto(String writerName){
        return CommentResponseDto.builder()
                .id(this.id)
                .clubId(this.clubId)
                .postId(this.post.getId())
                .writerId(this.writerId)
                .writerName(writerName)
                .content(this.content)
                .commentStep(this.commentStep)
                .commentOrder(this.commentOrder)
                .commentGroup(this.commentGroup)
                .createAt(this.getCreateDate())
                .updateAt(this.getUpdateDate())
                .build();
    }
    @Builder
    public Comment(Long id, Long clubId, Post post, Long writerId, String content, int commentStep, int commentOrder) {
        this.id = id;
        this.clubId = clubId;
        this.post = post;
        this.writerId = writerId;
        this.content = content;
        this.commentStep = commentStep;
        this.commentOrder = commentOrder;
    }
}
