package com.example.userservice.service;

import com.example.userservice.dto.user.request.RequestUpdateUser;
import com.example.userservice.dto.user.request.RequestUserName;
import com.example.userservice.dto.user.response.ResponseUserId;
import com.example.userservice.dto.user.response.ResponseUserName;
import org.springframework.stereotype.Service;


public interface UserService {

    ResponseUserId getUserId(Long userId);

    Long updateUserById(Long userId, RequestUpdateUser requestUpdateUser);

    void  deleteUser(Long userId);

    ResponseUserName getUserNameById(RequestUserName requestUserName);

    String getUserNameById(Long userId);

}
