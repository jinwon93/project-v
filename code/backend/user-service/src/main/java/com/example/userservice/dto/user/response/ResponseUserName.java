package com.example.userservice.dto.user.response;


import com.example.userservice.entity.User;
import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResponseUserName {
    private Map<String , String> userName;


    @Builder
    public ResponseUserName(List<User> userList) {
        this.userName = new HashMap<>();
        for (User user : userList) {
            userName.put(user.getId().toString() , user.getName());
        }
    }
}