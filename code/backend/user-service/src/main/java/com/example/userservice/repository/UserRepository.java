package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User , Long> {

    User findByLoginId(String loginId);

    List<User> findByIdIn(List<Long> userIds);
}
