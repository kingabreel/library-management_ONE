package one.alura.gablibrary.controller.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<BookResponse> results;

    public static ApiResponse fromJsonString(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, ApiResponse.class);
    }

}
