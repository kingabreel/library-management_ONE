package one.alura.gablibrary.service;

import one.alura.gablibrary.controller.external.ApiRequest;
import one.alura.gablibrary.controller.external.ApiResponse;
import one.alura.gablibrary.model.dtos.AuthorDto;
import one.alura.gablibrary.model.dtos.BookDto;
import one.alura.gablibrary.model.entities.Author;
import one.alura.gablibrary.model.entities.Book;
import one.alura.gablibrary.repository.AuthorRepository;
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

    @Autowired
    private AuthorRepository authorRepository;


    public void saveBook(Book book){
        List<Author> existingAuthors = authorRepository.findByName(book.getAuthor().getName());

        if (existingAuthors.isEmpty() || existingAuthors.get(0) == null) {
            saveAuthor(book.getAuthor());
        }

        List<Book> existingBook = repository.findByTitleContaining(book.getTitle());

        if (existingBook.isEmpty() || existingBook.get(0) == null) {
            repository.save(book);
        }
    }

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }
    public List<AuthorDto> getAllAuthor(){
        var list = authorRepository.findAll();
        List<AuthorDto> dtoList = new ArrayList<>();
        list.forEach(a -> dtoList.add(AuthorDto.fromRep(a)));

        return dtoList;
    }
    public List<AuthorDto> findAuthorsAliveInYear(int year){
        var list = authorRepository.findAuthorsAliveInYear(year);

        List<AuthorDto> dtoList = new ArrayList<>();
        list.forEach(author -> dtoList.add(AuthorDto.fromRep(author)));

        return dtoList;
    }
    public List<BookDto> getAllLocalBooks() {
        var list = repository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();

        list.forEach(b -> bookDtos.add(BookDto.fromRep(b)));
        return bookDtos;
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
            result.forEach(b -> {
                BookDto bookDto = new BookDto();
                AuthorDto authorDto = b.getAuthors().stream().findFirst().orElse(null);
                bookDto.setAuthor(authorDto);
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

    private List<BookDto> convertBook(List<Book> list){
        List<BookDto> books = new ArrayList<>();
        list.forEach(b -> books.add(BookDto.fromRep(b)));
        return books;
    }

    public List<BookDto> getBooksByLanguage(String language) {
        List<BookDto> list = new ArrayList<>();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("languages", language.toLowerCase());
        String response;
        try {
            response = ApiRequest.fetchBooks(queryParams);
            ApiResponse apiResponse = ApiResponse.fromJsonString(response);

            var result = apiResponse.getResults();
            System.out.println(result.size());

            result.forEach(b -> {
                BookDto bookDto = new BookDto();
                AuthorDto authorDto = b.getAuthors().stream().findFirst().orElse(null);
                bookDto.setAuthor(authorDto);
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
    public List<BookDto> searchByTopic(String topicSearch) {
        List<BookDto> list = new ArrayList<>();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("topic", topicSearch.toLowerCase());
        String response;
        try {
            response = ApiRequest.fetchBooks(queryParams);
            ApiResponse apiResponse = ApiResponse.fromJsonString(response);

            var result = apiResponse.getResults();
            System.out.println(result.size());

            result.forEach(b -> {
                BookDto bookDto = new BookDto();
                AuthorDto authorDto = b.getAuthors().stream().findFirst().orElse(null);
                bookDto.setAuthor(authorDto);
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

    public List<BookDto> searchByCopyRighting(boolean y) {
        List<BookDto> list = new ArrayList<>();
        String copy = y ? "true" : "false";

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("topic", copy);

        String response;
        try {
            response = ApiRequest.fetchBooks(queryParams);
            ApiResponse apiResponse = ApiResponse.fromJsonString(response);

            var result = apiResponse.getResults();
            System.out.println(result.size());

            result.forEach(b -> {
                BookDto bookDto = new BookDto();
                AuthorDto authorDto = b.getAuthors().stream().findFirst().orElse(null);
                bookDto.setAuthor(authorDto);
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
    public List<BookDto> searchById(int entityId) {
        List<BookDto> list = new ArrayList<>();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("search", String.valueOf(entityId));
        String response;
        try {
            response = ApiRequest.fetchBooks(queryParams);
            ApiResponse apiResponse = ApiResponse.fromJsonString(response);

            var result = apiResponse.getResults();
            result.forEach(b -> {
                BookDto bookDto = new BookDto();
                AuthorDto authorDto = b.getAuthors().stream().findFirst().orElse(null);
                bookDto.setAuthor(authorDto);
                bookDto.setLanguage(b.getLanguages().stream().findFirst().get());
                bookDto.setTitle(b.getTitle());
                bookDto.setSubject(b.getSubjects().stream().findFirst().orElse(null));
                bookDto.setDownloadCount((long) b.getDownload_count());

                list.add(bookDto);
            });

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public BookDto searchLocalById(Long id){
        return BookDto.fromRep(repository.findById(id).get());
    }

    public List<BookDto> searchLocalByTitle(String title){
        return convertBook(repository.findByTitleContaining(title));
    }

    public List<BookDto> searchLocalByLanguage(String language){
        return convertBook(repository.findByLanguage(language));
    }

    public List<BookDto> searchLocalByDownloadCount(Long min){
        return convertBook(repository.findByDownloadCountGreaterThanEqual(min));
    }

    public List<BookDto> searchLocalByAuthorId(Long authorId){
        return convertBook(repository.findByAuthorId(authorId));
    }

}
