package com.example.boardservice.service;

import com.example.boardservice.dto.comment.request.CommentUpdateRequestDto;
import com.example.boardservice.dto.comment.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto updateComment(Long commentId, Long writerId, CommentUpdateRequestDto commentUpdateRequestDto);
}
