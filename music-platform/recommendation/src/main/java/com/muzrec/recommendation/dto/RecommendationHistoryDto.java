package com.muzrec.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecommendationHistoryDto {

    private String login;
    private String trackId;
    private List<String> recommendedTrackIds;
    private LocalDateTime createdAt;

    public RecommendationHistoryDto(String login, String trackId, List<String> recommendedTrackIds, LocalDateTime createdAt) {
        this.login = login;
        this.trackId = trackId;
        this.recommendedTrackIds = recommendedTrackIds;
        this.createdAt = createdAt;
    }
}

