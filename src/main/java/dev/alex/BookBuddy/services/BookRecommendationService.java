package dev.alex.BookBuddy.services;

import dev.alex.BookBuddy.utils.SummaryTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chroma.vectorstore.ChromaApi;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRecommendationService {
    private final ChromaApi chroma;
    private final EmbeddingModel embeddingModel;
    private final ChatClient chatClient;
    private final SummaryTools summaryTools;

    private static final String TENANT = "default_tenant";
    private static final String DB = "default_database";
    private static final String COLLECTION = "book_summaries";

    BookRecommendationService(ChromaApi chroma, EmbeddingModel embeddingModel, ChatClient chatClient, SummaryTools summaryTools) {
        this.chroma = chroma;
        this.embeddingModel = embeddingModel;
        this.chatClient = chatClient;
        this.summaryTools = summaryTools;
    }

    private String recommend(String userQuery) {
        float[] q = embeddingModel.embed(userQuery);
        var col = chroma.getCollection(TENANT, DB, COLLECTION);
        if (col == null) return "";

        var req = new ChromaApi.QueryRequest(
                List.of(q),
                1,
                null,
                List.of(
                        ChromaApi.QueryRequest.Include.DOCUMENTS,
                        ChromaApi.QueryRequest.Include.METADATAS
                )
        );

        var resp = chroma.queryCollection(TENANT, DB, col.id(), req);
        if (resp == null || resp.documents() == null || resp.documents().isEmpty()) return "";

        var docs0 = resp.documents().get(0);
        if (docs0 == null || docs0.isEmpty()) return "";
        String summary = docs0.get(0);

        String title = "";
        var metasOuter = resp.metadata();
        if (metasOuter != null && !metasOuter.isEmpty()) {
            var meta0List = metasOuter.get(0);
            if (meta0List != null && !meta0List.isEmpty()) {
                var meta0 = meta0List.get(0);
                if (meta0 != null) {
                    Object t = meta0.get("title");
                    if (t instanceof String s) title = s;
                }
            }
        }
        return title.isBlank() ? summary : (title + "\n\n" + summary);
    }

    public String recommendWithRag(String question) {
        String context = recommend(question);
        return chatClient.prompt()
                .system("""
                        You are a helpful assistant. Use ONLY the CONTEXT to answer the user's question.
                        If the context is not enough, say you don't know.
                        
                        You need to:
                        1) Recommend a book based on the user's question.
                        2) Call tool getBookSummary with book's title to get the summary of the recommended book.
                        3) Create a message for the user that mimics a conversation with no further questions.
                        4) Include the book summary in your answer.
                        
                        CONTEXT:
                        """ + context).user(question).tools(summaryTools).call().content();
    }

}
