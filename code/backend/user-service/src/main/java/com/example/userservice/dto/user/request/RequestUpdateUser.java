package com.example.userservice.dto.user.request;


import com.example.userservice.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RequestUpdateUser {

    MultipartFile multipartFile;

    @Schema(description =  "이름")
    @Size(min = 2, message = "Name not be less than w characters")
    private String name;

    @Schema(description =  "휴대폰 번호")
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "Invalid phone number")
    private String phoneNumber;

    @Schema(description =  "생일")
    private LocalDate birthday;

    @Schema(description =  "성별")
    private Gender gender;
}
