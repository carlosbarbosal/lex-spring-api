package com.carlosbarbosal.lex_emotion_api.controller;

import com.carlosbarbosal.lex_emotion_api.dto.RedditPostResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carlosbarbosal.lex_emotion_api.service.RedditService;


@RestController
@RequestMapping("/reddit")
public class RedditController {

    private final RedditService redditService;

    public RedditController(RedditService redditService) {
        this.redditService = redditService;
    }


    @GetMapping("/user/{username}/submitted.json")
    public RedditPostResponse getHotPosts(@PathVariable String username) {
        return redditService.getHotPosts(username);
    }

}
