package com.carlosbarbosal.lex_emotion_api.controller;

import com.carlosbarbosal.lex_emotion_api.dto.RedditSearchResponse;
import com.carlosbarbosal.lex_emotion_api.service.RedditSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedditSearchController {

    private final RedditSearchService redditSearchService;

    public RedditSearchController(RedditSearchService redditSearchService) {
        this.redditSearchService = redditSearchService;
    }

    @GetMapping("/reddit/governo-lula")
    public List<RedditSearchResponse.RedditPostData> getLulaPosts() {
        return redditSearchService.getPostsRelatedToLula();
    }
}
