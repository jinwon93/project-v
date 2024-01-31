package com.example.boardservice.dto.post.request;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class PostUpdateRequestDto {

    private String category;
    private String title;
    private String comment;
    private String requestVoteDto;
}
