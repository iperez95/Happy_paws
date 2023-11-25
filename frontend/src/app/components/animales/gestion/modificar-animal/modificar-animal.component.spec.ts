import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarAnimalComponent } from './modificar-animal.component';

describe('ModificarAnimalComponent', () => {
  let component: ModificarAnimalComponent;
  let fixture: ComponentFixture<ModificarAnimalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModificarAnimalComponent]
    });
    fixture = TestBed.createComponent(ModificarAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
