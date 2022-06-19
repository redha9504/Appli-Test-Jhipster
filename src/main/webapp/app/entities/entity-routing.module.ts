import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'film',
        data: { pageTitle: 'appliTestJhipsterApp.film.home.title' },
        loadChildren: () => import('./film/film.module').then(m => m.FilmModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
