import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoGestionAnimalesComponent } from './listado-gestion-animales.component';

describe('ListadoGestionAnimalesComponent', () => {
  let component: ListadoGestionAnimalesComponent;
  let fixture: ComponentFixture<ListadoGestionAnimalesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListadoGestionAnimalesComponent]
    });
    fixture = TestBed.createComponent(ListadoGestionAnimalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
