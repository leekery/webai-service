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

    public Mono<Void> saveRecommendationHistory(String login, String trackName, List<String> recommendedTracks) {
        RecommendationHistory history = new RecommendationHistory(
                login,
                trackName,
                recommendedTracks,
                LocalDateTime.now()
        );
        log.info("Сохраняем историю для пользователя {}", login);
        return repository.save(history).then();
    }

    public Flux<RecommendationHistoryDto> getHistory(String login) {
        log.info("Получение истории для пользователя {}", login);
        return repository.findAllByLogin(login)
                .map(entity -> new RecommendationHistoryDto(
                        entity.getLogin(),
                        entity.getTrack(),
                        entity.getRecommendedTrackIds(),
                        entity.getCreatedAt()
                ));
    }
}
