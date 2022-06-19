export interface IFilm {
  id?: number;
  filmName?: string | null;
  realisateur?: string | null;
  disponible?: boolean | null;
}

export class Film implements IFilm {
  constructor(public id?: number, public filmName?: string | null, public realisateur?: string | null, public disponible?: boolean | null) {
    this.disponible = this.disponible ?? false;
  }
}

export function getFilmIdentifier(film: IFilm): number | undefined {
  return film.id;
}
