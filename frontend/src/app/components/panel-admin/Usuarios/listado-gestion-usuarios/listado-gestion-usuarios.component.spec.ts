import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoGestionUsuariosComponent } from './listado-gestion-usuarios.component';

describe('ListadoGestionUsuariosComponent', () => {
  let component: ListadoGestionUsuariosComponent;
  let fixture: ComponentFixture<ListadoGestionUsuariosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListadoGestionUsuariosComponent]
    });
    fixture = TestBed.createComponent(ListadoGestionUsuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
