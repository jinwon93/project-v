package com.example.boardservice.service;


import com.example.boardservice.client.ClientService;
import com.example.boardservice.dto.comment.request.CommentUpdateRequestDto;
import com.example.boardservice.dto.comment.response.CommentResponseDto;
import com.example.boardservice.entity.Comment;
import com.example.boardservice.repository.CommentRepository;

import com.example.boardservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl  implements CommentService{

    CommentRepository commentRepository;

    PostRepository postRepository;

    ClientService clientService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,  ClientService clientService) {
        this.commentRepository = commentRepository;
        this.clientService = clientService;
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, Long writerId, CommentUpdateRequestDto commentUpdateRequestDto) {

        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new RuntimeException("Comment Not found" + commentId));
            if (!comment.getWriterId().equals(writerId)) {
                throw new RuntimeException("User" + writerId + " is Not" + commentId);
            }

            comment.update(commentUpdateRequestDto);


            return comment.toResponseDto(clientService.getUserNameById(writerId));
        } catch (Exception e) {
            return null;
        }

    }
}
