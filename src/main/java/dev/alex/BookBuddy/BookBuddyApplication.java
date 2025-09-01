package dev.alex.BookBuddy;

import dev.alex.BookBuddy.services.BookRecommendationService;
import dev.alex.BookBuddy.services.EmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookBuddyApplication {

    @Autowired
    public BookRecommendationService bookRecommendationService;

    @Autowired
    public EmbeddingService embeddingService;

    public static void main(String[] args) {
        SpringApplication.run(BookBuddyApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup() {
        return args -> {
            embeddingService.insertEmbeddings();
//            System.out.println("WHAT A SUCCESS");
//            System.out.println(bookRecommendationService.recommendWithRag("I want to read a book that involves a tragedy"));
        };
    }
}
