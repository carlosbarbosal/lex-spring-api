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

    public RedditSearchService(RedditSearchApiClient redditSearchApiClient) {
        this.redditSearchApiClient = redditSearchApiClient;
    }

    public List<RedditSearchResponse.RedditPostData> getPostsRelatedToLula() {
        String query = "governo lula";
        String sort = "desc";
        String restrictSr = "on";
        String time = "all";

        RedditSearchResponse response = redditSearchApiClient.searchPosts(query, sort, restrictSr, time);

        // Obter a data de 2 anos atrás usando LocalDate
        LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
        Instant twoYearsAgoInstant = twoYearsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return response.getData().getChildren().stream()
                .filter(post -> {
                    Instant postTimestamp = Instant.ofEpochSecond(post.getData().getCreated_utc());

                    // Verifica se o título contém "governo lula" e se a postagem foi criada nos últimos 2 anos
                    return post.getData().getTitle().toLowerCase().contains("governo lula") &&
                            postTimestamp.isAfter(twoYearsAgoInstant) && postTimestamp.isBefore(Instant.now());
                })
                .collect(Collectors.toList());
    }
}
