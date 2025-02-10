package com.carlosbarbosal.lex_emotion_api.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.ejml.simple.SimpleMatrix;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SentimentAnalysisService {

    private final StanfordCoreNLP pipeline;

    public SentimentAnalysisService() {
        // Configuração do pipeline NLP com análise de sentimentos
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String analyzeSentiment(String text) {
        if (text == null || text.isEmpty()) {
            return "NEUTRAL";
        }

        // Criando um documento a partir do texto
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        // Percorrendo as frases do texto
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            // Extraindo a classe de sentimento da sentença
            SimpleMatrix sentimentValue = RNNCoreAnnotations.getPredictions(
                    sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class)
            );

            int sentiment;
            String sentimentClass = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

            switch (sentimentClass) {
                case "Very Negative" -> sentiment = 0;
                case "Negative" -> sentiment = 1;
                case "Neutral" -> sentiment = 2;
                case "Positive" -> sentiment = 3;
                case "Very Positive" -> sentiment = 4;
                default -> sentiment = 2; // Neutro como fallback
            }

// Mapeia o resultado para uma
// Mapeia o resultado para uma string descritiva
            return switch (sentiment) {
                case 0, 1 -> "NEGATIVE";
                case 3, 4 -> "POSITIVE";
                default -> "NEUTRAL";
            };
}
        return text;
    }
}

