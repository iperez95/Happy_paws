import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarProtectoraGestionComponent } from './editar-protectora-gestion.component';

describe('EditarProtectoraGestionComponent', () => {
  let component: EditarProtectoraGestionComponent;
  let fixture: ComponentFixture<EditarProtectoraGestionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarProtectoraGestionComponent]
    });
    fixture = TestBed.createComponent(EditarProtectoraGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
