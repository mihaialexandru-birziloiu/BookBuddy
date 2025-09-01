package dev.alex.BookBuddy.controllers;

import dev.alex.BookBuddy.dtos.RecommendationRequest;
import dev.alex.BookBuddy.services.BookRecommendationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationsController {
    private final BookRecommendationService bookRecommendationService;

    public RecommendationsController(BookRecommendationService bookRecommendationService) {
        this.bookRecommendationService = bookRecommendationService;
    }

    @PostMapping("/recommendations")
    public String getRecommendation(@RequestBody RecommendationRequest request) {
        return bookRecommendationService.recommendWithRag(request.getUserInput());
    }
}
