package com.example.userservice.client;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



public interface ClubServiceClient {

    @DeleteMapping("/club-service/users/{user-id}")
    void deleteUser(@PathVariable("user-id") Long userId);
}
