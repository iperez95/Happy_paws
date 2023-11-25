import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubirfotoAnimalComponent } from './subirfoto-animal.component';

describe('SubirfotoAnimalComponent', () => {
  let component: SubirfotoAnimalComponent;
  let fixture: ComponentFixture<SubirfotoAnimalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SubirfotoAnimalComponent]
    });
    fixture = TestBed.createComponent(SubirfotoAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
