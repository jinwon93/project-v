package com.example.userservice.entity;


import com.example.userservice.dto.user.request.RequestUpdateUser;
import com.example.userservice.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50 , unique = true)
    private String email;

    @Column(nullable = false)
    private String encryptedPwd;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private String profileImage;


    @Builder
    public User(String email, String encryptedPwd, String name, String phoneNumber, LocalDate birthday, Gender gender, String profileImage) {
        this.email = email;
        this.encryptedPwd = encryptedPwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.profileImage = profileImage;
    }



    public void updateEmail(String newEmail){
        this.email = newEmail;
    }

    public void updatePassword(String newPassword) {
        this.encryptedPwd = newPassword;
    }

    public void updateUser(RequestUpdateUser requestUpdateUser, String profileImageUrl) {
        if (StringUtils.hasText(requestUpdateUser.getName())) {
            this.name = requestUpdateUser.getName();
        }
        if (StringUtils.hasText(requestUpdateUser.getPhoneNumber())) {
            this.phoneNumber = requestUpdateUser.getPhoneNumber();
        }
        if (requestUpdateUser.getBirthday() != null) {
            this.birthday = requestUpdateUser.getBirthday();
        }

        if (requestUpdateUser.getGender() != null) {
            this.gender = requestUpdateUser.getGender();
        }
        if (StringUtils.hasText(profileImageUrl)) {
            this.profileImage = profileImageUrl;
        }
    }
}
