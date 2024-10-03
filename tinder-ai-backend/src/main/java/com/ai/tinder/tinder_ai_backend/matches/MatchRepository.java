package com.ai.tinder.tinder_ai_backend.matches;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, String> {
    Optional<Match> findByProfileId(String profileId); // Add this method
}
