import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuestionarioAdopcionComponent } from './cuestionario-adopcion.component';

describe('CuestionarioAdopcionComponent', () => {
  let component: CuestionarioAdopcionComponent;
  let fixture: ComponentFixture<CuestionarioAdopcionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CuestionarioAdopcionComponent]
    });
    fixture = TestBed.createComponent(CuestionarioAdopcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
