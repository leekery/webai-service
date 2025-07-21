package com.muzrec.recommendation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RecommendationResponseDto {
    private String trackId;
    private List<String> recommendedTrackIds;
}
