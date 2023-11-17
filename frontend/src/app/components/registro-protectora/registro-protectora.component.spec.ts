import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroProtectoraComponent } from './registro-protectora.component';

describe('RegistroProtectoraComponent', () => {
  let component: RegistroProtectoraComponent;
  let fixture: ComponentFixture<RegistroProtectoraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistroProtectoraComponent]
    });
    fixture = TestBed.createComponent(RegistroProtectoraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
