import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFilm } from '../film.model';

@Component({
  selector: 'jhi-film-detail',
  templateUrl: './film-detail.component.html',
})
export class FilmDetailComponent implements OnInit {
  film: IFilm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ film }) => {
      this.film = film;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
