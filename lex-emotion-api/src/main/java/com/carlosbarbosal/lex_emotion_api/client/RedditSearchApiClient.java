package com.carlosbarbosal.lex_emotion_api.client;

import com.carlosbarbosal.lex_emotion_api.dto.RedditSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "redditSearchApiClient", url = "https://www.reddit.com")
public interface RedditSearchApiClient {

    @GetMapping("/search.json")
    RedditSearchResponse searchPosts(@RequestParam("q") String query,
                                     @RequestParam("sort") String sort,
                                     @RequestParam("restrict_sr") String restrictSr,
                                     @RequestParam("t") String time);
}
