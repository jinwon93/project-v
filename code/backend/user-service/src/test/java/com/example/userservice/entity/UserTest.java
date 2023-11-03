package com.example.userservice.entity;

import com.example.userservice.enums.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;



class UserTest {


    @Test
    @DisplayName("유저 테스트")
    void createUser() {

        User user = User.builder()
                .email("test@nate.com")
                .encryptedPwd("1234555")
                .name("kim")
                .phoneNumber("010-1234-5678")
                .birthday(LocalDate.of(2023 , 01 ,27))
                .gender(Gender.MALE)
                .profileImage("imageUrl")
                .build();


        Assertions.assertThat(user.getName()).isEqualTo("kim");
        Assertions.assertThat(user.getPhoneNumber()).isEqualTo("010-1234-5678");
    }

}