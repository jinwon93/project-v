package com.example.boardservice.client;

import com.example.boardservice.dto.user.request.UserNameRequest;
import com.example.boardservice.dto.user.response.UserNameResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(name="user-service")
public interface ClientService {

    @PostMapping("/user/userNames")
    UserNameResponse getUserName(UserNameRequest userNameRequest);


    @GetMapping("/user/{user-id}/userName")
    String getUserNameById(@PathVariable("user-id") Long userId);


    @GetMapping("/user/{user-name}")
    Long getUserIdByName(@PathVariable("user-name") String userName);
}
