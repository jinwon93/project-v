package com.example.userservice.controller;


import com.example.userservice.dto.user.request.RequestUpdateUser;
import com.example.userservice.dto.user.request.RequestUpdateUserEmail;
import com.example.userservice.dto.user.request.RequestUpdateUserPassword;
import com.example.userservice.dto.user.request.RequestUserName;
import com.example.userservice.dto.user.response.ResponseUserId;
import com.example.userservice.dto.user.response.ResponseUserName;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/{user-id}")
    public ResponseEntity<ResponseUserId> getUserById(@PathVariable("user-id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserId(userId));
    }

    //채팅 부분 떄문에 부분적인 리소스 업데이트를 위해서 Patch로 이 부분을 계속 고민!
    @PatchMapping("/{user-id}")
    public ResponseEntity<Long> updateUser(@PathVariable("user-id") Long userId , @Valid @ModelAttribute RequestUpdateUser request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(userId , request));
    }


    @PatchMapping("/{user-id}/email")
    public ResponseEntity<Long> updateUserEmail(@PathVariable("user-id") Long userId , RequestUpdateUserEmail request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserEmail(userId , request));
    }

    @PatchMapping("/{user-id}/password")
    public ResponseEntity<Long> updateUserPassword(@PathVariable("user-id") Long userId , RequestUpdateUserPassword request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserPassword(userId , request));
    }
    @DeleteMapping("/{user-id}")
    public  ResponseEntity<Void> deleteUser(@PathVariable("user-id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/userNames")
    public ResponseEntity<ResponseUserName> getUserNamesByIds(@RequestBody RequestUserName request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserNameByIds(request));
    }

    @GetMapping("/{user-id}/userName")
    public ResponseEntity<String> getUserNamesById(@PathVariable("user-id") Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserNameById(userId));
    }


    @GetMapping("/{user-name}")
    public ResponseEntity<Long> getUserByName(@PathVariable("user-id") String name){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserIdByName(name));
    }
}
