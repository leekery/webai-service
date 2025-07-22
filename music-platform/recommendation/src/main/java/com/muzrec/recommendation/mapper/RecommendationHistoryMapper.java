package com.muzrec.recommendation.mapper;

import com.muzrec.recommendation.dto.RecommendationHistoryDto;
import com.muzrec.recommendation.model.RecommendationHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecommendationHistoryMapper {

    RecommendationHistoryDto toDto(RecommendationHistory entity);

    RecommendationHistory toEntity(RecommendationHistoryDto dto);
}
