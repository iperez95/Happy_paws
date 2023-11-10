import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactoProtectoraComponent } from './contacto-protectora.component';

describe('ContactoProtectoraComponent', () => {
  let component: ContactoProtectoraComponent;
  let fixture: ComponentFixture<ContactoProtectoraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactoProtectoraComponent]
    });
    fixture = TestBed.createComponent(ContactoProtectoraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
