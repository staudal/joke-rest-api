package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ChuckDTO;
import dtos.DadDTO;
import dtos.MyJokeDTO;
import utils.EMF_Creator;
import facades.JokeFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final JokeFacade FACADE =  JokeFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("chuck")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getChuckJoke(){
        ChuckDTO chuckDTO = FACADE.fetchChuck();
        return Response.ok().entity(chuckDTO).build();
    }

    @GET
    @Path("dad")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDadJoke(){
        DadDTO dadDTO = FACADE.fetchDad();
        return Response.ok().entity(dadDTO).build();
    }

    @GET
    @Path("combined")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCombinedJoke(){
        MyJokeDTO myJokeDTO = new MyJokeDTO(FACADE.fetchChuck(), FACADE.fetchDad());
        return Response.ok().entity(myJokeDTO).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postExample(String input){
        ChuckDTO rmdto = GSON.fromJson(input, ChuckDTO.class);
        System.out.println(rmdto);
        return Response.ok().entity(rmdto).build();
    }
}
