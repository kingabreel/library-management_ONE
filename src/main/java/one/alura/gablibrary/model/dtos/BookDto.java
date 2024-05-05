package one.alura.gablibrary.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.alura.gablibrary.model.entities.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private AuthorDto author;
    private String subject;
    private String language;
    private Long downloadCount;

    public static BookDto fromRep(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(AuthorDto.fromRep(book.getAuthor()));
        bookDto.setTitle(book.getTitle());
        bookDto.setSubject(book.getSubject());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setDownloadCount(book.getDownloadCount());

        return bookDto;
    }

    @Override
    public String toString(){
        return "{\n\ttitle: " + getTitle() + "\n\tauthor: " + getAuthor() + "\n\tSubject: " + getSubject() + "\n\tLanguage: " + getLanguage() + "\n\tDownloads: " + getDownloadCount();
    }
}
