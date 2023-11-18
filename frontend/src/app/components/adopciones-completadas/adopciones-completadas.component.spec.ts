import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdopcionesCompletadasComponent } from './adopciones-completadas.component';

describe('AdopcionesCompletadasComponent', () => {
  let component: AdopcionesCompletadasComponent;
  let fixture: ComponentFixture<AdopcionesCompletadasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdopcionesCompletadasComponent]
    });
    fixture = TestBed.createComponent(AdopcionesCompletadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
