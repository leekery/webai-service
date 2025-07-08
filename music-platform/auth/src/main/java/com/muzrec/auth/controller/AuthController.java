package com.muzrec.auth.controller;

import com.muzrec.auth.dto.JwtResponse;
import com.muzrec.auth.dto.UserDto;
import com.muzrec.auth.dto.UserLoginDto;
import com.muzrec.auth.dto.UserRegisterDto;
import com.muzrec.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody UserRegisterDto dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody UserLoginDto dto) {
        return service.login(dto);
    }
}
