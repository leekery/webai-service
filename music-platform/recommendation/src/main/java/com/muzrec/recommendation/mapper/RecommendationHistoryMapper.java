package com.muzrec.recommendation.mapper;

import com.muzrec.recommendation.dto.RecommendationHistoryDto;
import com.muzrec.recommendation.model.RecommendationHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendationHistoryMapper {

    RecommendationHistoryDto toDto(RecommendationHistory entity);

    @Mapping(target = "id", ignore = true)
    RecommendationHistory toEntity(RecommendationHistoryDto dto);
}
