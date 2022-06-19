package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Film.
 */
@Entity
@Table(name = "film")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "realisateur")
    private String realisateur;

    @Column(name = "disponible")
    private Boolean disponible;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Film id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilmName() {
        return this.filmName;
    }

    public Film filmName(String filmName) {
        this.setFilmName(filmName);
        return this;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getRealisateur() {
        return this.realisateur;
    }

    public Film realisateur(String realisateur) {
        this.setRealisateur(realisateur);
        return this;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public Boolean getDisponible() {
        return this.disponible;
    }

    public Film disponible(Boolean disponible) {
        this.setDisponible(disponible);
        return this;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film)) {
            return false;
        }
        return id != null && id.equals(((Film) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Film{" +
            "id=" + getId() +
            ", filmName='" + getFilmName() + "'" +
            ", realisateur='" + getRealisateur() + "'" +
            ", disponible='" + getDisponible() + "'" +
            "}";
    }
}
