package com.muzrec.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/auth/public-key")
@RequiredArgsConstructor
public class PublicKeyController {

    private final RSAPublicKey rsaPublicKey;

    @GetMapping
    public String getPublicKey() {
        log.info("Выдача публичного RSA ключа");
        return Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
    }
}
