package entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film",schema = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;

    private String title;

    private String description;

    private String releaseYear;

    @Column(name = "language_id")
    private Short languageId;

    @Column(name = "original_language_id")
    private Short originalLanguageId;

    @Column(name = "rental_duration")
    private Short rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    private Integer length;

    @Column(name = "replacment_cost")
    private BigDecimal replacementCost;

    @Column(columnDefinition = "enum('G','PG','PG-13','R','NC-17')")
    private String rating;

    @Column(name = "last_update")
    private String lastUpdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    public Film(Integer filmId, String title, Integer length) {
        this.filmId = filmId;
        this.title = title;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId + ",\n" +
                "title='" + title + "',\n" +
                "length=" + length + ",\n" +
                "actors=" + actors + "\n" +
                '}';
    }
}
