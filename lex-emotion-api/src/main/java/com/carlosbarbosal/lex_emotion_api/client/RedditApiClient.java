package com.carlosbarbosal.lex_emotion_api.client;

import com.carlosbarbosal.lex_emotion_api.dto.RedditPostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "redditApiClient", url = "https://oauth.reddit.com")
public interface RedditApiClient {

    @GetMapping(value = "/user/{username}/submitted.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    RedditPostResponse getHotPosts(@RequestHeader("Authorization") String bearerToken,
                                   @PathVariable("username") String username);
}
