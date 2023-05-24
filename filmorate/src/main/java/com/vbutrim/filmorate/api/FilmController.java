package com.vbutrim.filmorate.api;

import com.vbutrim.filmorate.film.FilmStorage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
public class FilmController {

    private final FilmStorage filmStorage;

    public FilmController(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Valid
    @PostMapping
    public void createFilm(@Valid @RequestBody FilmToCreateDto filmToCreate) {
        System.out.println("xx");
        // filmStorage.save(...)
    }

    static final class FilmToCreateDto {
        @NotNull
        private LocalDate date;
    }
}
