package one.alura.gablibrary.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.alura.gablibrary.model.entities.Author;
import one.alura.gablibrary.model.entities.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private String birth_year;
    private String death_year;

    public static AuthorDto fromRep(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setBirth_year(author.getBirthY());
        authorDto.setDeath_year(author.getDeathY());

        return authorDto;
    }
}
