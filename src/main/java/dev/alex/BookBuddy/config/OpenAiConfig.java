package dev.alex.BookBuddy.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultOptions(OpenAiChatOptions.builder().model("gpt-4.1-nano").temperature(0.2).build()).build();
    }
}
