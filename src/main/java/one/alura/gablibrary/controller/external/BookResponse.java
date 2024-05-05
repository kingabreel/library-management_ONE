package one.alura.gablibrary.controller.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import one.alura.gablibrary.model.dtos.AuthorDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookResponse {
    private int id;
    private String title;
    private List<AuthorDto> authors;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private boolean copyright;
    private String media_type;
    private Map<String, String> formats;
    private int download_count;

    public static BookResponse fromJsonString(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, BookResponse.class);
    }
}
