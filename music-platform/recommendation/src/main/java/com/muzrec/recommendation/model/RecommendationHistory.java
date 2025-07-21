package com.muzrec.recommendation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table("recommendation_history")
public class RecommendationHistory {

    @Id
    private Long id;
    private String userId;
    private String trackId;

    @Column("recommended_track_ids")
    private List<String> recommendedTrackIds;
    private LocalDateTime createdAt;

    public RecommendationHistory(Long id, String userId, String trackId, List<String> recommendedTrackIds, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.trackId = trackId;
        this.recommendedTrackIds = recommendedTrackIds;
        this.createdAt = createdAt;
    }
}
