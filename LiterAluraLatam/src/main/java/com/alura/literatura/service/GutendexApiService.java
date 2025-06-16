package com.alura.literatura.service;

import com.alura.literatura.model.Author;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.literatura.model.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class GutendexApiService {

    private static final String API_URL = "https://gutendex.com/books/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Book> searchBookByTitle(String title) {
        try {
            String url = API_URL + "?search=" + title.replace(" ", "%20");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                JsonNode results = root.path("results");

                if (results.isArray() && results.size() > 0) {
                    JsonNode bookNode = results.get(0); // Tomamos el primer resultado

                    String bookTitle = bookNode.path("title").asText();
                    String language = bookNode.path("languages").get(0).asText(); // Asumiendo un solo idioma
                    Long downloadCount = bookNode.path("download_count").asLong();

                    // Obtener autor
                    JsonNode authorsNode = bookNode.path("authors");
                    Author author = null;
                    if (authorsNode.isArray() && authorsNode.size() > 0) {
                        JsonNode authorNode = authorsNode.get(0);
                        String authorName = authorNode.path("name").asText();
                        Integer birthYear = authorNode.has("birth_year") && !authorNode.path("birth_year").isNull() ? authorNode.path("birth_year").asInt() : null;
                        Integer deathYear = authorNode.has("death_year") && !authorNode.path("death_year").isNull() ? authorNode.path("death_year").asInt() : null;
                        author = new Author(authorName, birthYear, deathYear);
                    }
                    return Optional.of(new Book(bookTitle, author, language, downloadCount)); // <--- ORDEN DE LOS PARÁMETROS: title, author, language, downloadCount
                }
            } else {
                System.err.println("Error al buscar libro en Gutendex. Código de estado: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error de comunicación con la API de Gutendex: " + e.getMessage());
        }
        return Optional.empty();
    }
}