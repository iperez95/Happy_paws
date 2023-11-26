import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoGestionProtectorasComponent } from './listado-gestion-protectoras.component';

describe('ListadoGestionProtectorasComponent', () => {
  let component: ListadoGestionProtectorasComponent;
  let fixture: ComponentFixture<ListadoGestionProtectorasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListadoGestionProtectorasComponent]
    });
    fixture = TestBed.createComponent(ListadoGestionProtectorasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
