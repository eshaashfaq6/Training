package com.project.second.session2.RestTemplate;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
public class NewsApiClient {

    private final RestTemplate restTemplate;

    public NewsApiClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<News> findNews() {
        String result = restTemplate
                .getForEntity("https://newsapi.org/v2/top-headlines/sources?apiKey=ceeb61cc970f4a08bc6a85110fdd2ced&category=technology", String.class).getBody();
        News news = new News();
        news.setDetails(result);
        System.out.println(result);
        return List.of(news);
    }
}
