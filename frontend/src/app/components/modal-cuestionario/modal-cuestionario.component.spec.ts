import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCuestionarioComponent } from './modal-cuestionario.component';

describe('ModalCuestionarioComponent', () => {
  let component: ModalCuestionarioComponent;
  let fixture: ComponentFixture<ModalCuestionarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalCuestionarioComponent]
    });
    fixture = TestBed.createComponent(ModalCuestionarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
