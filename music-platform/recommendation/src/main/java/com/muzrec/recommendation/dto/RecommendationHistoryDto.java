package com.muzrec.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecommendationHistoryDto {

    private String userId;
    private String trackId;
    private List<String> recommendedTrackIds;
    private LocalDateTime createdAt;

    public RecommendationHistoryDto(String userId, String trackId, List<String> recommendedTrackIds, LocalDateTime createdAt) {
        this.userId = userId;
        this.trackId = trackId;
        this.recommendedTrackIds = recommendedTrackIds;
        this.createdAt = createdAt;
    }
}

