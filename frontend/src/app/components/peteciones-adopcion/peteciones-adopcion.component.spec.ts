import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetecionesAdopcionComponent } from './peteciones-adopcion.component';

describe('PetecionesAdopcionComponent', () => {
  let component: PetecionesAdopcionComponent;
  let fixture: ComponentFixture<PetecionesAdopcionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PetecionesAdopcionComponent]
    });
    fixture = TestBed.createComponent(PetecionesAdopcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
