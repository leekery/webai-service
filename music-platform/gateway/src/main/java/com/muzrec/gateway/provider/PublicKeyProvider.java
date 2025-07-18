package com.muzrec.gateway.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicKeyProvider {

    private final WebClient.Builder webClientBuilder;
    private volatile PublicKey publicKey;

    public Mono<PublicKey> fetchPublicKey() {
        return webClientBuilder.build().get()
                .uri("lb://auth/api/auth/public-key")
                .retrieve()
                .bodyToMono(String.class)
                .map(this::convertPemToPublicKey)
                .doOnNext(key -> this.publicKey = key);
    }

    public Mono<PublicKey> getPublicKey() {
        if (publicKey != null) {
            return Mono.just(publicKey);
        }
        return fetchPublicKey();
    }

    private PublicKey convertPemToPublicKey(String pem) {
        try {

            String publicKeyPEM = pem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");

            byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert PEM to PublicKey", e);
        }
    }
}
