package one.alura.gablibrary.service;

import one.alura.gablibrary.controller.external.ApiRequest;
import one.alura.gablibrary.controller.external.ApiResponse;
import one.alura.gablibrary.model.dtos.BookDto;
import one.alura.gablibrary.model.entities.Book;
import one.alura.gablibrary.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository repository;

    public List<BookDto> findByAuthorName(String name){
        var list = repository.findByAuthorName(name);

        return convertBook(list);
    }

    public List<BookDto> findByBookName(String name){
        var list = repository.findByTitleContaining(name);

        return convertBook(list);
    }

    private List<BookDto> convertBook(List<Book> list){
        List<BookDto> books = new ArrayList<>();
        list.forEach(b -> {
            books.add(BookDto.fromRep(b));
        });
        return books;
    }

    public List<BookDto> getBooksBySearch(String term) {
        List<BookDto> list = new ArrayList<>();

        term = term.replace("-", "%20");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("search", term);
        String response;
        try {
            response = ApiRequest.fetchBooks(queryParams);
            ApiResponse apiResponse = ApiResponse.fromJsonString(response);

            var result = apiResponse.getResults();
            int id = 0;
            result.forEach(b -> {
                BookDto bookDto = new BookDto();
                bookDto.setAuthor(b.getAuthors().get(id));
                bookDto.setLanguage(b.getLanguages().stream().findFirst().get());
                bookDto.setTitle(b.getTitle());
                bookDto.setSubject(b.getSubjects().stream().findFirst().get());
                bookDto.setDownloadCount((long) b.getDownload_count());

                list.add(bookDto);
            });

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
