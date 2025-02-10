package com.carlosbarbosal.lex_emotion_api.controller;

import com.carlosbarbosal.lex_emotion_api.dto.AccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carlosbarbosal.lex_emotion_api.service.RedditService;


@RestController
@RequestMapping("/reddit/auth")
public class RedditAuthController {

    private final RedditService redditService;

    public RedditAuthController(RedditService redditService) {
        this.redditService = redditService;
    }


    @PostMapping("/token")
    public AccessTokenResponse getAccessToken() {
        return redditService.getAccessToken();
    }
}
