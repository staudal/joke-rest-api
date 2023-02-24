package dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MyJokeDTO {
    private String id;
    private List<String> jokes;

    public MyJokeDTO(ChuckDTO chuckDTO, DadDTO dadDTO) {
        this.id = chuckDTO.getId() + " - " + dadDTO.getId();
        this.jokes = List.of(chuckDTO.getValue(), dadDTO.getJoke());
    }
}
