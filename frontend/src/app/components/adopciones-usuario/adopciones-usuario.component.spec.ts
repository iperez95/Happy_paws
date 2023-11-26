import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdopcionesUsuarioComponent } from './adopciones-usuario.component';

describe('AdopcionesUsuarioComponent', () => {
  let component: AdopcionesUsuarioComponent;
  let fixture: ComponentFixture<AdopcionesUsuarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdopcionesUsuarioComponent]
    });
    fixture = TestBed.createComponent(AdopcionesUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
