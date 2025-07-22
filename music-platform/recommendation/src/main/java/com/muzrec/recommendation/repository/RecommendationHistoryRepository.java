package com.muzrec.recommendation.repository;

import com.muzrec.recommendation.model.RecommendationHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface RecommendationHistoryRepository extends ReactiveCrudRepository<RecommendationHistory, UUID> {
    Flux<RecommendationHistory> findAllByUserId(String userId);
}
