import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimaldetalladoComponent } from './animaldetallado.component';

describe('AnimaldetalladoComponent', () => {
  let component: AnimaldetalladoComponent;
  let fixture: ComponentFixture<AnimaldetalladoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnimaldetalladoComponent]
    });
    fixture = TestBed.createComponent(AnimaldetalladoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
