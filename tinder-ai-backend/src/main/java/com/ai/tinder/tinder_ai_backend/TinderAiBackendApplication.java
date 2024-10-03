package com.ai.tinder.tinder_ai_backend;

import com.ai.tinder.tinder_ai_backend.conversations.ConversationRepository;
import com.ai.tinder.tinder_ai_backend.matches.MatchRepository;
import com.ai.tinder.tinder_ai_backend.profiles.ProfileCreationService;
import com.ai.tinder.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {
	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	@Autowired
	private ProfileCreationService profileCreationService;

	@Autowired
	private MatchRepository matchRepository;




	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}

	public void run(String... args) {
		clearAllData();
		profileCreationService.saveProfilesToDB();
	}

	private void clearAllData() {
		conversationRepository.deleteAll();
		matchRepository.deleteAll();
		profileRepository.deleteAll();
	}

}
