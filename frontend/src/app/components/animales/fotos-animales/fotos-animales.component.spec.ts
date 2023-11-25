import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FotosAnimalesComponent } from './fotos-animales.component';

describe('FotosAnimalesComponent', () => {
  let component: FotosAnimalesComponent;
  let fixture: ComponentFixture<FotosAnimalesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FotosAnimalesComponent]
    });
    fixture = TestBed.createComponent(FotosAnimalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
