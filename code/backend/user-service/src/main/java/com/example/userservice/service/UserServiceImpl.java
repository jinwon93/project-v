package com.example.userservice.service;

import com.example.userservice.client.ClubServiceClient;
import com.example.userservice.dto.user.request.RequestUpdateUserEmail;
import com.example.userservice.dto.user.request.RequestUpdateUser;
import com.example.userservice.dto.user.request.RequestUpdateUserPassword;
import com.example.userservice.dto.user.request.RequestUserName;
import com.example.userservice.dto.user.response.ResponseUserId;
import com.example.userservice.dto.user.response.ResponseUserName;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.file.FileStore;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ClubServiceClient clubServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final FileStore fileStore;

    @Override
    public ResponseUserId getUserId(Long userId) {

        User user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return ResponseUserId.builder().user(user).build();

    }

    @Transactional
    @Override
    public Long updateUserById(Long userId, RequestUpdateUser requestUpdateUser) {
        User user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String profileImageUrl = null;

        try {
            fileStore.deleteFile(user.getProfileImage());
            profileImageUrl = fileStore.storeFile(requestUpdateUser.getMultipartFile());

        } catch (IOException e) {
            log.error("Failed to update profile image for user with ID: {}", userId, e);
        }

        user.updateUser(requestUpdateUser, profileImageUrl);

        return userId;
    }

    @Override
    public Long updateUserEmail(Long userId, RequestUpdateUserEmail requestUpdateUserEmail) {
        User user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        String newEmail = passwordEncoder.encode(requestUpdateUserEmail.getNewEmail());
        user.updateEmail(newEmail);
        return user.getId();
    }


    @Override
    public Long updateUserPassword(Long userId, RequestUpdateUserPassword requestUpdateUserPassword) {
        User user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        String newEncryptPassword = passwordEncoder.encode(requestUpdateUserPassword.getNewPassword());
        user.updatePassword(newEncryptPassword);
        return user.getId();

    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        repository.delete(user);
        clubServiceClient.deleteUser(userId);
    }

    @Override
    public ResponseUserName getUserNameByIds(RequestUserName requestUserName) {
        List<User> userList = repository.findByIdIn(requestUserName.getUserId());
        return ResponseUserName.builder().userList(userList).build();
    }

    @Override
    public String getUserNameById(Long userId){
        User user = repository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return user.getName();
    }



    @Override
    public Long getUserIdByName(String name){
        User user = repository.findByName(name)
                .orElseThrow(UserNotFoundException::new);

        return user.getId();
    }
}
