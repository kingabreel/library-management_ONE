package one.alura.gablibrary.controller;

import one.alura.gablibrary.model.dtos.BookDto;
import one.alura.gablibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService service;

    @GetMapping("/books")
    public List<BookDto> getBooks(@RequestParam String term){
        return service.getBooksBySearch(term);
    }
}
