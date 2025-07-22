package com.muzrec.recommendation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table("recommendation_history")
public class RecommendationHistory {

    private String login;
    private String track;
    @Column("recommended_track_ids")
    private List<String> recommendedTrackIds;
    private LocalDateTime createdAt;

    public RecommendationHistory(String login, String track, List<String> recommendedTrackIds, LocalDateTime createdAt) {
        this.login = login;
        this.track = track;
        this.recommendedTrackIds = recommendedTrackIds;
        this.createdAt = createdAt;
    }
}
