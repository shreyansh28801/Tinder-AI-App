package com.ai.tinder.tinder_ai_backend.conversations;

import com.ai.tinder.tinder_ai_backend.matches.MatchController;
import com.ai.tinder.tinder_ai_backend.profiles.Profile;
import com.ai.tinder.tinder_ai_backend.profiles.ProfileController;
import com.ai.tinder.tinder_ai_backend.profiles.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@CrossOrigin
public class ConversationController {
    public static final Logger logger = LoggerFactory.getLogger(ConversationController.class);

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationService conversationService;
    @Autowired
    private ProfileController profileController;

    @CrossOrigin(origins = "*")
    @PostMapping("/conversation")
    public Conversation createConversation(@RequestBody CreateConversationRequest request) {
        profileRepository.findById(request.profileId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find profile with Id `" + request.profileId + "`"
                ));

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId,
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        return conversation;
    }

    @GetMapping("/conversation/{conversationId}")
    public Conversation fetchConversation(
            @PathVariable String conversationId
    ) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find conversation with Id `" + conversationId + "`"
                ));
        return conversation;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/addMessageToconversation/{conversationId}")
    public Conversation addessageToConversation(
            @PathVariable String conversationId,
            @RequestBody ChatMessage chatMessage
    ){
        logger.info("addessageToConversation {}",chatMessage);
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find conversation with Id `" + conversationId + "`"
                ));

        Profile profile = profileRepository.findById(conversation.profileId())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find profile with Id `" + conversation.profileId() + "`"
                ));
        Profile user = profileRepository.findById(chatMessage.authorId())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find profile with Id `" + chatMessage.authorId() + "`"
                ));
        ChatMessage chatMessageWithNewTime = new ChatMessage(
                chatMessage.messageText(),
                chatMessage.authorId(),
                LocalDateTime.now()
        );
        // TODO: Need to validate that the author of a message happens to be only the profile associated with the
        //  message or the user ONLY

        conversation.messages().add(chatMessageWithNewTime);

        conversationService.generateProfileResponse(conversation, profile, user);
        conversationRepository.save(conversation);
        return conversation;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/doChat/{prompt}")
    public String doChat(@PathVariable String prompt)//to test the ollama integration this spring application
    {
        String ans = ollamaChatModel.call(prompt);
        System.out.println(ans);
        return ans;
    }

    public record CreateConversationRequest (
            String profileId
    ){}
}
