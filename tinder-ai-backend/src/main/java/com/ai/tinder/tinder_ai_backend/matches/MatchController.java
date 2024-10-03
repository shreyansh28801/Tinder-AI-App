package com.ai.tinder.tinder_ai_backend.matches;

import com.ai.tinder.tinder_ai_backend.conversations.Conversation;
import com.ai.tinder.tinder_ai_backend.conversations.ConversationRepository;
import com.ai.tinder.tinder_ai_backend.profiles.Profile;
import com.ai.tinder.tinder_ai_backend.profiles.ProfileRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MatchController {
    public static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    private final MatchRepository matchRepository;


    public MatchController(ConversationRepository conversationRepository, ProfileRepository profileRepository, MatchRepository matchRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
        this.matchRepository = matchRepository;
    }

    public record CreateMatchRequest(String profileId) {}

    @CrossOrigin(origins = "*")
    @PostMapping("/matches")
    public Match createNewMatch(@RequestBody CreateMatchRequest request) {
        logger.info("Creating new match for profileId : {}", request.profileId);

        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find a profile with ID " + request.profileId()
                ));

        // TODO: Make sure there are no existing conversations with this profile already

        Optional<Match> matchOptional = matchRepository.findByProfileId(profile.id());
        if (matchOptional.isPresent()) {
            logger.error("Match already exist for profileId : {}", request.profileId());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A conversation with this profile already exists."
            );
        }

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                profile.id(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        Match match = new Match(
                UUID.randomUUID().toString(),
                profile,
                conversation.id()
        );
        matchRepository.save(match);
        return match;

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
