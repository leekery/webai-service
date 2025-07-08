package com.muzrec.recommendation.service;

import com.muzrec.recommendation.dto.SongDto;
import com.muzrec.recommendation.exception.AiServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final WebClient client;

    public List<SongDto> getRecommendations(String trackName) throws RuntimeException {
        try {
            return client.get()
                    .uri(uriBuilder -> uriBuilder.path("/recommendations")
                            .queryParam("trackName", trackName)
                            .build())
                    .retrieve()
                    .bodyToFlux(SongDto.class)
                    .collectList()
                    .block();
        } catch (AiServiceException e) {
            throw new AiServiceException("Сервис рекомендаций недоступен");
        }
    }
}
