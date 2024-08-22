package service;

import jakarta.inject.Inject;
import entity.Film;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import repository.FilmRepository;

import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
@AllArgsConstructor
public class FilmService {

    @Inject
    FilmRepository filmRepository;

    public Film findById(Integer id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + id));
    }


    public List<Film> findAll(Long page, Long minLength, Long pageSize) {
        return filmRepository.findAll(page, minLength,pageSize);
    }

    public List<Film> actors(String startsWith) {
        return filmRepository.actors(startsWith);
    }
}


