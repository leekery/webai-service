package com.muzrec.auth.service;

import com.muzrec.auth.dto.JwtResponse;
import com.muzrec.auth.dto.UserDto;
import com.muzrec.auth.dto.UserLoginDto;
import com.muzrec.auth.dto.UserRegisterDto;
import com.muzrec.auth.exception.BadRequestException;
import com.muzrec.auth.exception.JwtTokenException;
import com.muzrec.auth.exception.UnauthorizedException;
import com.muzrec.auth.mapper.UserMapper;
import com.muzrec.auth.model.User;
import com.muzrec.auth.repository.UserRepository;
import com.muzrec.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDto register(UserRegisterDto dto) {

        if (repository.findByLogin(dto.getLogin()).isPresent()) {
            throw new BadRequestException("Логин уже существует");
        }

        User user = mapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toUserDto(repository.save(user));
    }

    public JwtResponse login(UserLoginDto dto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword())
            );
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);
            return new JwtResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Неверный логин или пароль");
        }
    }
}
