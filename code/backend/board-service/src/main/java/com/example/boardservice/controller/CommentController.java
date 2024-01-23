package com.example.boardservice.controller;


import com.example.boardservice.service.CommentService;
import org.hibernate.cfg.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CommentController {

    Environment env;
    CommentService commentService;

}
