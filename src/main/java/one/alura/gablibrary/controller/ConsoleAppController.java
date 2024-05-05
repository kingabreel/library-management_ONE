package one.alura.gablibrary.controller;

import one.alura.gablibrary.model.dtos.BookDto;
import one.alura.gablibrary.model.entities.Book;
import one.alura.gablibrary.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

public class ConsoleAppController {
    private LibraryService service;
    private List<String> languagesAcepted;

    public ConsoleAppController(LibraryService service) {
        this.service = service;
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
}
