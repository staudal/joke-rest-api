package facades;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

//import errorhandling.RenameMeNotFoundException;
import com.google.gson.Gson;
import dtos.ChuckDTO;
import dtos.DadDTO;
import dtos.MyJokeDTO;
import lombok.NoArgsConstructor;
import utils.EMF_Creator;

@NoArgsConstructor
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ChuckDTO fetchChuck() {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.chucknorris.io/jokes/random")).header("accept", "application/json").build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        ChuckDTO chuckDTO = gson.fromJson(response.body(), ChuckDTO.class);

        return chuckDTO;
    }

    public DadDTO fetchDad() {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://icanhazdadjoke.com/")).header("accept", "application/json").build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        DadDTO dadDTO = gson.fromJson(response.body(), DadDTO.class);

        return dadDTO;
    }
}
