package com.carlosbarbosal.lex_emotion_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RedditPostResponse {

    @JsonProperty("data")
    private RedditData data;

    public RedditData getData() {
        return data;
    }

    public void setData(RedditData data) {
        this.data = data;
    }

    public static class RedditData {

        @JsonProperty("children")
        private List<RedditPostData> children;

        public List<RedditPostData> getChildren() {
            return children;
        }

        public void setChildren(List<RedditPostData> children) {
            this.children = children;
        }
    }

    public static class RedditPostData {

        @JsonProperty("data")
        private PostData data;

        public PostData getData() {
            return data;
        }

        public void setData(PostData data) {
            this.data = data;
        }
    }

    public static class PostData {
        private String title;
        private String url;

        @JsonProperty("selftext")
        private String selftext;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSelftext() {
            return selftext;
        }

        public void setSelftext(String selftext) {
            this.selftext = selftext;
        }
    }

    @Override
    public String toString() {
        return "RedditPostResponse{" +
                "data=" + data +
                '}';
    }
}
