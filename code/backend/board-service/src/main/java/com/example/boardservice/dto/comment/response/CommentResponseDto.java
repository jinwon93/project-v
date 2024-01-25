package com.example.boardservice.dto.comment.response;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private Long clubId;
    private Long postId;
    private Long writerId;
    private String writerName;
    private String content;
    private int commentStep;
    private int commentGroup;
    private int commentOrder;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Builder
    public CommentResponseDto(Long id, Long clubId, Long postId, Long writerId, String writerName, String content, int commentStep, int commentGroup, int commentOrder, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.clubId = clubId;
        this.postId = postId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.commentStep = commentStep;
        this.commentGroup = commentGroup;
        this.commentOrder = commentOrder;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
