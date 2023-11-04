import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionProtectoraComponent } from './gestion-protectora.component';

describe('GestionProtectoraComponent', () => {
  let component: GestionProtectoraComponent;
  let fixture: ComponentFixture<GestionProtectoraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionProtectoraComponent]
    });
    fixture = TestBed.createComponent(GestionProtectoraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
