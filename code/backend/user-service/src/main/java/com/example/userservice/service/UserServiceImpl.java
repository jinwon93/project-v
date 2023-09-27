package com.example.userservice.service;

import com.example.userservice.client.ClubServiceClient;
import com.example.userservice.dto.user.request.RequestUpdateUser;
import com.example.userservice.dto.user.request.RequestUserName;
import com.example.userservice.dto.user.response.ResponseUserId;
import com.example.userservice.dto.user.response.ResponseUserName;
import com.example.userservice.file.FileStore;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements  UserService{

    private final UserRepository repository;
    private final ClubServiceClient clubServiceClient;
    private final FileStore fileStore;
    @Override
    public ResponseUserId getUserId(Long userId) {
        return null;
    }

    @Override
    public Long updateUserById(Long userId, RequestUpdateUser requestUpdateUser) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public ResponseUserName getUserNameById(RequestUserName requestUserName) {
        return null;
    }

    @Override
    public String getUserNameById(Long userId) {
        return null;
    }
}
