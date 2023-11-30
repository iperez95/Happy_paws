import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarAnimalGestionComponent } from './editar-animal-gestion.component';

describe('EditarAnimalGestionComponent', () => {
  let component: EditarAnimalGestionComponent;
  let fixture: ComponentFixture<EditarAnimalGestionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarAnimalGestionComponent]
    });
    fixture = TestBed.createComponent(EditarAnimalGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
