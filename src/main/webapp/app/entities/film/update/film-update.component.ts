import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IFilm, Film } from '../film.model';
import { FilmService } from '../service/film.service';

@Component({
  selector: 'jhi-film-update',
  templateUrl: './film-update.component.html',
})
export class FilmUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    filmName: [],
    realisateur: [],
    disponible: [],
  });

  constructor(protected filmService: FilmService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ film }) => {
      this.updateForm(film);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const film = this.createFromForm();
    if (film.id !== undefined) {
      this.subscribeToSaveResponse(this.filmService.update(film));
    } else {
      this.subscribeToSaveResponse(this.filmService.create(film));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFilm>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(film: IFilm): void {
    this.editForm.patchValue({
      id: film.id,
      filmName: film.filmName,
      realisateur: film.realisateur,
      disponible: film.disponible,
    });
  }

  protected createFromForm(): IFilm {
    return {
      ...new Film(),
      id: this.editForm.get(['id'])!.value,
      filmName: this.editForm.get(['filmName'])!.value,
      realisateur: this.editForm.get(['realisateur'])!.value,
      disponible: this.editForm.get(['disponible'])!.value,
    };
  }
}
