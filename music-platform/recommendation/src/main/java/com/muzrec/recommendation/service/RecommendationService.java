package com.muzrec.recommendation.service;

import com.muzrec.recommendation.dto.SongDto;
import com.muzrec.recommendation.exception.AiServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final WebClient client;

    public Mono<List<SongDto>> getRecommendations(String trackName) throws RuntimeException {
        try {
            log.info("Получаем данные с AI сервиса");
            return client.get()
                    .uri(uriBuilder -> uriBuilder.path("/recommendations")
                            .queryParam("trackName", trackName)
                            .build())
                    .retrieve()
                    .bodyToFlux(SongDto.class)
                    .collectList();
        } catch (AiServiceException e) {
            throw new AiServiceException("Сервис рекомендаций недоступен");
        }
    }
}
