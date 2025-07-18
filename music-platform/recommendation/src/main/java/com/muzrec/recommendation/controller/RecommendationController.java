package com.muzrec.recommendation.controller;

import com.muzrec.recommendation.dto.SongDto;
import com.muzrec.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService service;

    @GetMapping
    public List<SongDto> getRecommendation(@RequestParam String trackName) {
        log.info("Запрос на получение рекомендаций трека {}", trackName);
        return service.getRecommendations(trackName);
    }

}
