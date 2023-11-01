package com.example.userservice.dto.user.response;


import com.example.userservice.entity.User;
import com.example.userservice.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ResponseUserId {

    @Schema(description = "유저 번호")
    private Long id;

    @Schema(description = "로그인 아이디")
    private String email;

    @Schema(description = "유저 이름")
    private String name;

    @Schema(description = "핸드폰 번호")
    private String phoneNumber;

    @Schema(description = "생일")
    private LocalDate birthday;

    @Schema(description = "성병")
    private Gender gender;

    @Schema(description = "프로필 이미지 URL")
    private String profileImage;


    @Builder
    public ResponseUserId(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.birthday = user.getBirthday();
        this.gender = user.getGender();
        this.profileImage = user.getProfileImage();
    }
}
