package com.carlosbarbosal.lex_emotion_api.service;

import com.carlosbarbosal.lex_emotion_api.client.RedditSearchApiClient;
import com.carlosbarbosal.lex_emotion_api.dto.RedditSearchResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedditSearchService {

    private final RedditSearchApiClient redditSearchApiClient;
    private final SentimentAnalysisService sentimentAnalysisService;


    public RedditSearchService(RedditSearchApiClient redditSearchApiClient, SentimentAnalysisService sentimentAnalysisService) {
        this.redditSearchApiClient = redditSearchApiClient;
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    public List<RedditSearchResponse.RedditPostData> getPostsRelatedToLula() {
        String query = "governo lula";
        String sort = "desc";
        String restrictSr = "on";
        String time = "all";

        // Obter a data de 2 anos atrás usando LocalDate
        RedditSearchResponse response = redditSearchApiClient.searchPosts(query, sort, restrictSr, time);
        long twoYearsAgo = Instant.now().minusSeconds(60L * 60 * 24 * 365 * 2).getEpochSecond();

        return response.getData().getChildren().stream()
                .filter(post -> post.getData().getCreated_utc() >= twoYearsAgo)
                .map(post -> {
                    String sentiment = sentimentAnalysisService.analyzeSentiment(post.getData().getTitle());
                    post.getData().setSentiment(sentiment);  // Adiciona a análise ao post
                    return post;
                })
                .collect(Collectors.toList());
    }
}
