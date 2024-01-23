package com.example.boardservice.service;


import com.example.boardservice.repository.CommentRepository;

import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl  implements CommentService{

    CommentRepository commentRepository;
}
