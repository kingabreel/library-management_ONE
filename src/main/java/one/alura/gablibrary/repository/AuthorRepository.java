package one.alura.gablibrary.repository;

import one.alura.gablibrary.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
    @Query("SELECT a FROM Author a WHERE a.birthY <= :year AND (a.deathY >= :year OR a.deathY IS NULL)")
    List<Author> findAuthorsAliveInYear(int year);
}
