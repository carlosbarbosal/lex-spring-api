package com.carlosbarbosal.lex_emotion_api.service;

import com.carlosbarbosal.lex_emotion_api.client.RedditApiClient;
import com.carlosbarbosal.lex_emotion_api.client.RedditAuthClient;
import com.carlosbarbosal.lex_emotion_api.dto.AccessTokenResponse;
import com.carlosbarbosal.lex_emotion_api.dto.RedditPostResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class RedditService {

    private final RedditAuthClient authClient;
    private final RedditApiClient apiClient;

    @Value("${reddit.clientId}")
    private String clientId;

    @Value("${reddit.clientSecret}")
    private String clientSecret;

    @Value("${reddit.username}")
    private String username;

    @Value("${reddit.password}")
    private String password;

    public RedditService(RedditAuthClient authClient, RedditApiClient apiClient) {
        this.authClient = authClient;
        this.apiClient = apiClient;
    }


    public AccessTokenResponse getAccessToken() {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        return authClient.getAccessToken(authHeader, "password", username, password);
    }

    public RedditPostResponse getHotPosts(String subreddit) {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        AccessTokenResponse tokenResponse = authClient.getAccessToken(authHeader, "password", username, password);
        String bearerToken = "Bearer " + tokenResponse.getAccess_token();
        return apiClient.getHotPosts(bearerToken, subreddit);
    }
}

