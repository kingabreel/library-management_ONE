package one.alura.gablibrary.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.alura.gablibrary.model.dtos.BookDto;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    private Author author;
    private String subject;
    private String language;
    private Long downloadCount;

    public static Book fromDto(BookDto bookDto){
        Book book = new Book();
        Author author = new Author();
        author.setName(bookDto.getAuthor().getName());
        author.setBirthY(bookDto.getAuthor().getBirth_year());
        author.setDeathY(bookDto.getAuthor().getDeath_year());

        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        book.setDownloadCount(bookDto.getDownloadCount());
        book.setLanguage(bookDto.getLanguage());
        book.setSubject(bookDto.getSubject());

        return book;
    }
}
