package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Film;
import com.mycompany.myapp.repository.FilmRepository;
import com.mycompany.myapp.service.FilmService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Film}.
 */
@RestController
@RequestMapping("/api")
public class FilmResource {

    private final Logger log = LoggerFactory.getLogger(FilmResource.class);

    private static final String ENTITY_NAME = "film";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FilmService filmService;

    private final FilmRepository filmRepository;

    public FilmResource(FilmService filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    /**
     * {@code POST  /films} : Create a new film.
     *
     * @param film the film to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new film, or with status {@code 400 (Bad Request)} if the film has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/films")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) throws URISyntaxException {
        log.debug("REST request to save Film : {}", film);
        if (film.getId() != null) {
            throw new BadRequestAlertException("A new film cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Film result = filmService.save(film);
        return ResponseEntity
            .created(new URI("/api/films/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /films/:id} : Updates an existing film.
     *
     * @param id the id of the film to save.
     * @param film the film to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated film,
     * or with status {@code 400 (Bad Request)} if the film is not valid,
     * or with status {@code 500 (Internal Server Error)} if the film couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/films/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable(value = "id", required = false) final Long id, @RequestBody Film film)
        throws URISyntaxException {
        log.debug("REST request to update Film : {}, {}", id, film);
        if (film.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, film.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!filmRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Film result = filmService.update(film);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, film.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /films/:id} : Partial updates given fields of an existing film, field will ignore if it is null
     *
     * @param id the id of the film to save.
     * @param film the film to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated film,
     * or with status {@code 400 (Bad Request)} if the film is not valid,
     * or with status {@code 404 (Not Found)} if the film is not found,
     * or with status {@code 500 (Internal Server Error)} if the film couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/films/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Film> partialUpdateFilm(@PathVariable(value = "id", required = false) final Long id, @RequestBody Film film)
        throws URISyntaxException {
        log.debug("REST request to partial update Film partially : {}, {}", id, film);
        if (film.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, film.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!filmRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Film> result = filmService.partialUpdate(film);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, film.getId().toString())
        );
    }

    /**
     * {@code GET  /films} : get all the films.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of films in body.
     */
    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Films");
        Page<Film> page = filmService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /films/:id} : get the "id" film.
     *
     * @param id the id of the film to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the film, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable Long id) {
        log.debug("REST request to get Film : {}", id);
        Optional<Film> film = filmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(film);
    }

    /**
     * {@code DELETE  /films/:id} : delete the "id" film.
     *
     * @param id the id of the film to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        log.debug("REST request to delete Film : {}", id);
        filmService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
