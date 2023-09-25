package com.example.userservice.entity;


import com.example.userservice.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String loginId;

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
    public User(String loginId, String encryptedPwd, String name, String phoneNumber, LocalDate birthday, Gender gender, String profileImage) {
        this.loginId = loginId;
        this.encryptedPwd = encryptedPwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.profileImage = profileImage;
    }
}
