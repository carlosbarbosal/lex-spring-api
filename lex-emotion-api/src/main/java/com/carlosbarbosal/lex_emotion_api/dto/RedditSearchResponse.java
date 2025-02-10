package com.carlosbarbosal.lex_emotion_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RedditSearchResponse {

    @JsonProperty("data")
    private RedditSearchData data;

    public RedditSearchData getData() {
        return data;
    }

    public void setData(RedditSearchData data) {
        this.data = data;
    }

    public static class RedditSearchData {
        
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
        private long created_utc;  // Timestamp da criação
        private String subreddit;  // Nome do subreddit
        private int num_comments;  // Número de comentários
        private int score;         // Pontuação de upvotes/downvotes
        private String author;     // Autor da postagem


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

        public long getCreated_utc() {
            return created_utc;
        }

        public void setCreated_utc(long created_utc) {
            this.created_utc = created_utc;
        }

        public String getSubreddit() {
            return subreddit;
        }

        public void setSubreddit(String subreddit) {
            this.subreddit = subreddit;
        }

        public int getNum_comments() {
            return num_comments;
        }

        public void setNum_comments(int num_comments) {
            this.num_comments = num_comments;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "PostData{" +
                    "title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", created_utc=" + created_utc +
                    ", subreddit='" + subreddit + '\'' +
                    ", num_comments=" + num_comments +
                    ", score=" + score +
                    ", author='" + author + '\'' +
                    '}';
        }
    }
}
