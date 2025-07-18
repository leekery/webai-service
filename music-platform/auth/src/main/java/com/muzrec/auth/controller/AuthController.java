package com.muzrec.auth.controller;

import com.muzrec.auth.dto.JwtResponse;
import com.muzrec.auth.dto.UserDto;
import com.muzrec.auth.dto.UserLoginDto;
import com.muzrec.auth.dto.UserRegisterDto;
import com.muzrec.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody UserRegisterDto dto) {
        log.info("Регистрация пользователя c логином: {}", dto.getLogin());
        return service.register(dto);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody UserLoginDto dto) {
        log.info("Попытка входа с логином: {}", dto.getLogin());
        return service.login(dto);
    }
}
