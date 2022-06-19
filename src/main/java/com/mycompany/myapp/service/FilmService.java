package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Film;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Film}.
 */
public interface FilmService {
    /**
     * Save a film.
     *
     * @param film the entity to save.
     * @return the persisted entity.
     */
    Film save(Film film);

    /**
     * Updates a film.
     *
     * @param film the entity to update.
     * @return the persisted entity.
     */
    Film update(Film film);

    /**
     * Partially updates a film.
     *
     * @param film the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Film> partialUpdate(Film film);

    /**
     * Get all the films.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Film> findAll(Pageable pageable);

    /**
     * Get the "id" film.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Film> findOne(Long id);

    /**
     * Delete the "id" film.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
