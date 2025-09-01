package dev.alex.BookBuddy.services;

import org.springframework.ai.chroma.vectorstore.ChromaApi;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmbeddingService {
    private final ChromaApi chromaApi;
    private final EmbeddingModel embeddingModel;

    public EmbeddingService(ChromaApi chromaApi, EmbeddingModel embeddingModel) {
        this.chromaApi = chromaApi;
        this.embeddingModel = embeddingModel;
    }

    public void insertEmbeddings() {
        ClassPathResource resource = new ClassPathResource("book_summaries.txt");
        String text;
        try (var in = resource.getInputStream()) {
            text = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChromaApi.Collection summariesCollection = chromaApi.getCollection("default_tenant", "default_database", "book_summaries");
        if (summariesCollection == null) {
            summariesCollection = chromaApi.createCollection("default_tenant", "default_database", new ChromaApi.CreateCollectionRequest("book_summaries"));
        }
        Pattern H = Pattern.compile("^##+\\s*(.+)\\s*$", Pattern.MULTILINE);
        Matcher m = H.matcher(text);

        var ids = new ArrayList<String>();
        var vectors = new ArrayList<float[]>();
        var metadatas = new ArrayList<Map<String, Object>>();
        var documents = new ArrayList<String>();

        int bodyStart = -1;
        String title = null;
        java.util.function.Function<String, String> slug = s ->
                s.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");

        while (m.find()) {
            if (title != null) {
                String body = text.substring(bodyStart, m.start()).trim();
                if (!body.isEmpty()) {
                    String bookId = slug.apply(title);
                    vectors.add(embeddingModel.embed(body));
                    ids.add(bookId);
                    metadatas.add(Map.of("book_id", bookId, "title", title));
                    documents.add(body);
                }
            }
            title = m.group(1).trim();
            bodyStart = m.end();
        }
        if (title != null) {
            String body = text.substring(bodyStart).trim();
            if (!body.isEmpty()) {
                String bookId = slug.apply(title);
                vectors.add(embeddingModel.embed(body));
                ids.add(bookId);
                metadatas.add(Map.of("book_id", bookId, "title", title));
                documents.add(body);
            }
        }
        chromaApi.upsertEmbeddings(
                "default_tenant", "default_database", summariesCollection.id(),
                new ChromaApi.AddEmbeddingsRequest(ids, vectors, metadatas, documents)
        );
    }
}
