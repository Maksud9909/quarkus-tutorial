package repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import entity.Film;
import entity.Film$;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Film> findById(Integer id) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(id))
                .findFirst();
    }

    @Transactional
    public List<Film> findAll(Long page, Long minLength, Long pageSize) {
        return jpaStreamer.stream(Projection.select(Film$.filmId,Film$.title,Film$.length))
                .filter(Film$.length.greaterThan(Math.toIntExact(minLength)))
                .sorted(Film$.length)
                .skip(page * pageSize)
                .toList();
    }


    @Transactional
    public List<Film> actors(String startsWith) {
        StreamConfiguration<Film> sc = StreamConfiguration.of(Film.class).joining(Film$.actors);
        return jpaStreamer.stream(sc)
                .filter(Film$.title.startsWith(startsWith))
                .collect(Collectors.toList());
    }

}
