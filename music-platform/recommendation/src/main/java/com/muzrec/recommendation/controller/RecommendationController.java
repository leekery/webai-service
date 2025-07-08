package com.muzrec.recommendation.controller;

import com.muzrec.recommendation.dto.SongDto;
import com.muzrec.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService service;

    @GetMapping
    public List<SongDto> getRecommendation(@RequestParam String trackName) {
        return service.getRecommendations(trackName);
    }

}
