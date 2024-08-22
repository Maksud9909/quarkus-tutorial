package resource;

import entity.Film;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import service.FilmService;

import java.util.List;

@Path("/films")
public class FilmResource {
    @Inject
    FilmService filmService;

    @GET
    @Path("/getFilm/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Film getFilm(@PathParam("filmId") Integer filmId) {
        return filmService.findById(filmId);
    }

    @GET
    @Path("/paged/{page}/{minLength}{pageSize}")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Film> getFilms(@QueryParam("page") Long page,
                               @QueryParam("minLength") Long minLength,
                               @QueryParam("pageSize") Long pageSize) {
        return filmService.findAll(page, minLength,pageSize);
    }


    @GET
    @Path("/actors/{startsWith}")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Film> actors(@PathParam("startsWith") String startsWith) {
        return filmService.actors(startsWith);
    }


}
