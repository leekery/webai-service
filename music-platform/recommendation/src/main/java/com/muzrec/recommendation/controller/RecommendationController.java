package com.muzrec.recommendation.controller;

import com.muzrec.recommendation.dto.RecommendationHistoryDto;
import com.muzrec.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService service;

    @GetMapping("/search")
    public Mono<List<String>> getRecommendation(@RequestParam String trackName) {
        log.info("Запрос на получение рекомендаций трека {}", trackName);
        return service.getRecommendations(trackName);
    }

    @PostMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> saveHistory(@RequestParam String trackName,
                            @RequestHeader("X-User-Login") String login,
                            @RequestBody List<String> recommendedTracks) {
        log.info("Запрос на сохранение истории по треку {}, для пользователя {}", trackName, login);
        return service.saveRecommendationHistory(login, trackName, recommendedTracks);
    }

    @GetMapping("/history")
    public Flux<RecommendationHistoryDto> getHistory(@RequestHeader("X-User-Login") String login) {
        log.info("Запрос на получение истории для пользователя {}", login);
        return service.getHistory(login);
    }
}
