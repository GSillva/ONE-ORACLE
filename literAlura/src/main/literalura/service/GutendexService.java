package com.example.literalura.service;

import com.example.literalura.model.Author;
import com.example.literalura.model.Book;
import com.example.literalura.repository.AuthorRepository;
import com.example.literalura.repository.BookRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GutendexService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public GutendexService(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public void searchAndSaveBook(String title) {
        String url = "https://gutendex.com/books?search=" + title.replace(" ", "%20");
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray("results");

        if (results.isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
            return;
        }

        JSONObject bookJson = results.getJSONObject(0);

        Book book = new Book();
        book.setTitle(bookJson.getString("title"));
        book.setLanguage(bookJson.getJSONArray("languages").getString(0));
        book.setDownloadCount(bookJson.getInt("download_count"));

        JSONArray authorsJson = bookJson.getJSONArray("authors");
        for (int i = 0; i < authorsJson.length(); i++) {
            String authorName = authorsJson.getJSONObject(i).getString("name");
            Author author = new Author();
            author.setName(authorName);
            book.getAuthors().add(author);
        }

        bookRepo.save(book);
        System.out.println("Livro salvo: " + book.getTitle());
    }

    public void listBooks() {
        bookRepo.findAll().forEach(b ->
            System.out.println(b.getTitle() + " (" + b.getLanguage() + ")")
        );
    }

    public void listAuthors() {
        authorRepo.findAll().forEach(a ->
            System.out.println(a.getName())
        );
    }

    public void listBooksByLanguage(String lang) {
        bookRepo.findAll().stream()
            .filter(b -> b.getLanguage().equalsIgnoreCase(lang))
            .forEach(b -> System.out.println(b.getTitle()));
    }
}
