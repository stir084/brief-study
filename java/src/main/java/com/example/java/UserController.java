package com.example.java;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        // user 객체가 유효한지 여부를 확인하고 유효하지 않으면 예외가 발생합니다.
        // 유효한 경우 처리 로직을 추가하십시오.
        System.out.println(user.id +"--" + user.username);
        return ResponseEntity.ok( "User created successfully");
    }

    @Data
    public static class User {
        @NotNull(message = "\"hostId\": You must provide hostId.")
        private Long id;

        @NotBlank
        @Size(min = 2, max = 50)
        private String username;

        @Email
        private String email;

        // 생성자, 게터, 세터 등 필요한 메서드 추가
    }
}