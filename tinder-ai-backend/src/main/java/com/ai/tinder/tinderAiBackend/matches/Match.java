package com.ai.tinder.tinderAiBackend.matches;

import com.ai.tinder.tinderAiBackend.profiles.Profile;

public record Match (String id, Profile profile, String conversationId) {
}
