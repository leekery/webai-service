package com.muzrec.recommendation.service;

import com.muzrec.recommendation.dto.RecommendationHistoryDto;
import com.muzrec.recommendation.exception.AiServiceException;
import com.muzrec.recommendation.model.RecommendationHistory;
import com.muzrec.recommendation.repository.RecommendationHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final WebClient client;
    private final RecommendationHistoryRepository repository;

    public Mono<List<String>> getRecommendations(String trackName) throws RuntimeException {
        try {
            log.info("Получаем данные с AI сервиса");
            return client.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/recommendations")
                            .queryParam("trackName", trackName)
                            .build())
                    .retrieve()
                    .bodyToFlux(String.class)
                    .collectList();
        } catch (AiServiceException e) {
            throw new AiServiceException("Сервис рекомендаций недоступен");
        }
    }

    public void saveRecommendationHistory(String userId, String trackName, List<String> recommendedTracks) {
        RecommendationHistory history = new RecommendationHistory(
                null,
                userId,
                trackName,
                recommendedTracks,
                LocalDateTime.now()
        );
        repository.save(history).then();
        log.info("История сохранена");
    }

    public Flux<RecommendationHistoryDto> getHistory(String userId) {
        log.info("Получение истории для пользователя {}", userId);
        return repository.findAllByUserId(userId)
                .map(entity -> new RecommendationHistoryDto(
                        entity.getUserId(),
                        entity.getTrackId(),
                        entity.getRecommendedTrackIds(),
                        entity.getCreatedAt()
                ));
    }
}
