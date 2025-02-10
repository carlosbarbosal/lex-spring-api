package com.carlosbarbosal.lex_emotion_api.client;

import com.carlosbarbosal.lex_emotion_api.dto.AccessTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "redditAuthClient", url = "https://www.reddit.com/api/v1")
public interface RedditAuthClient {

    @PostMapping(value = "/access_token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AccessTokenResponse getAccessToken(@RequestHeader("Authorization") String authorization,
                                       @RequestParam("grant_type") String grantType,
                                       @RequestParam("username") String username,
                                       @RequestParam("password") String password);
}
