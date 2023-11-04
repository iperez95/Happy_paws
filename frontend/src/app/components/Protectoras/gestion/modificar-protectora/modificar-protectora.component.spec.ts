import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarProtectoraComponent } from './modificar-protectora.component';

describe('ModificarProtectoraComponent', () => {
  let component: ModificarProtectoraComponent;
  let fixture: ComponentFixture<ModificarProtectoraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModificarProtectoraComponent]
    });
    fixture = TestBed.createComponent(ModificarProtectoraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
