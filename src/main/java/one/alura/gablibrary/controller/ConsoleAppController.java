package one.alura.gablibrary.controller;

import one.alura.gablibrary.model.dtos.BookDto;
import one.alura.gablibrary.model.entities.Book;
import one.alura.gablibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleAppController {
    @Autowired
    private LibraryService service;
    private List<String> languagesAcepted;

    public ConsoleAppController() {
        this.languagesAcepted = new ArrayList<>();
        fillLanguageList();
    }

    private void fillLanguageList(){
        languagesAcepted.add("pt");
        languagesAcepted.add("fr");
        languagesAcepted.add("de");
        languagesAcepted.add("en");
        languagesAcepted.add("it");
        languagesAcepted.add("el");
    }
    public void saveLocalDb(List<BookDto> bookList) {
        bookList.forEach(b -> {
            service.saveBook(Book.fromDto(b));
        });
    }

    public List<BookDto> searchByTitleOrName(String title) {
        return service.getBooksBySearch(title);
    }

    public List<BookDto> searchByLanguage(String language){
        return service.getBooksByLanguage(language);
    }

    public boolean languageAccepted(String language){
        return languagesAcepted.contains(language);
    }

    public void saveToLocalTxt(List<BookDto> books){
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/searchBackup/backup.txt";

        try (FileWriter writer = new FileWriter(filePath)) {
            for (BookDto book : books) {
                writer.write(book.toString() + "\n\n");
            }
            System.out.println("Books saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }

    public List<BookDto> searchByTopic(String topicSearch) {
        return service.searchByTopic(topicSearch);
    }
    public List<BookDto> searchByCopyrighting(boolean y) {
        return service.searchByCopyRighting(y);
    }
    public BookDto searchById(int id) {
        return service.searchById(id).stream().findFirst().get();
    }

    public List<BookDto> searchLocalByTitle(String title){
        return service.searchLocalByTitle(title);
    }

    public List<BookDto> searchLocalByLanguage(String language) {
        return service.searchLocalByLanguage(language);
    }

    public BookDto searchLocalById(int bookId) {
        return service.searchLocalById((long) bookId);
    }

    public List<BookDto> searchLocalByDownloadCount(int downCount) {
        return service.searchLocalByDownloadCount((long) downCount);
    }

    public List<BookDto> searchLocalByAuthorId(int authorId) {
        return service.searchLocalByAuthorId((long) authorId);
    }


}
