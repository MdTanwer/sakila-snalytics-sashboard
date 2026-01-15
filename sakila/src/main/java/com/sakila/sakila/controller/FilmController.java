package com.sakila.analytics.controller;

import com.sakila.analytics.model.entity.Film;
import com.sakila.analytics.repository.FilmRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @QueryMapping
    public List<Film> films() {
        return filmRepository.findAll();
    }

    @QueryMapping
    public Film filmById(@Argument Long id) {
        return filmRepository.findById(id).orElse(null);
    }
}
