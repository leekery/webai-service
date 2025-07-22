package com.muzrec.recommendation.repository;

import com.muzrec.recommendation.model.RecommendationHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface RecommendationHistoryRepository extends ReactiveCrudRepository<RecommendationHistory, UUID> {
    Flux<RecommendationHistory> findAllByLogin(String login);
}
