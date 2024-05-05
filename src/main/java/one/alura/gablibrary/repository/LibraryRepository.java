package one.alura.gablibrary.repository;

import one.alura.gablibrary.model.entities.Author;
import one.alura.gablibrary.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String name);
    List<Book> findByLanguage(String language);
    List<Book> findByDownloadCountGreaterThanEqual(Long downloadCount);
    List<Book> findByAuthorId(Long authorId);
}
