import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FilmDetailComponent } from './film-detail.component';

describe('Film Management Detail Component', () => {
  let comp: FilmDetailComponent;
  let fixture: ComponentFixture<FilmDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilmDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ film: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(FilmDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FilmDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load film on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.film).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
