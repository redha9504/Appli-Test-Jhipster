package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Film;
import com.mycompany.myapp.repository.FilmRepository;
import com.mycompany.myapp.service.FilmService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Film}.
 */
@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film save(Film film) {
        log.debug("Request to save Film : {}", film);
        return filmRepository.save(film);
    }

    @Override
    public Film update(Film film) {
        log.debug("Request to save Film : {}", film);
        return filmRepository.save(film);
    }

    @Override
    public Optional<Film> partialUpdate(Film film) {
        log.debug("Request to partially update Film : {}", film);

        return filmRepository
            .findById(film.getId())
            .map(existingFilm -> {
                if (film.getFilmName() != null) {
                    existingFilm.setFilmName(film.getFilmName());
                }
                if (film.getRealisateur() != null) {
                    existingFilm.setRealisateur(film.getRealisateur());
                }
                if (film.getDisponible() != null) {
                    existingFilm.setDisponible(film.getDisponible());
                }

                return existingFilm;
            })
            .map(filmRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Film> findAll(Pageable pageable) {
        log.debug("Request to get all Films");
        return filmRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Film> findOne(Long id) {
        log.debug("Request to get Film : {}", id);
        return filmRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Film : {}", id);
        filmRepository.deleteById(id);
    }
}
