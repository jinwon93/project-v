package com.example.userservice.dto.user.request;


import com.example.userservice.entity.User;
import com.example.userservice.enums.Gender;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RequestSignUpDto {



    @Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$" , message = "Email format is not correct!")
    private String email;

    @Pattern(regexp = "^(?:(?=.*[a-zA-Z])(?=.*[\\W_])|(?=.*[a-zA-Z])(?=.*\\d)|(?=.*\\d)(?=.*[\\W_])).{8,}$",
            message = "Password must be at least 8 characters long and combine at least two of the following: letters, numbers, and special symbols.")
    private String password;

    private String name;

    private String phoneNumber;
    private LocalDate birthday;

    private Gender gender;


    public User toEntity(String encryptedPwd) {
        User user = User.builder()
                .email(email)
                .encryptedPwd(encryptedPwd)
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .build();
        return user;
    }
}
