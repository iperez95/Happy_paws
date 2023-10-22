import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProtectorasComponent } from './protectoras.component';

describe('ProtectorasComponent', () => {
  let component: ProtectorasComponent;
  let fixture: ComponentFixture<ProtectorasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProtectorasComponent]
    });
    fixture = TestBed.createComponent(ProtectorasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
