import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryRegistrationComponent } from './inventory-registration.component';

describe('InventoryRegistrationComponent', () => {
  let component: InventoryRegistrationComponent;
  let fixture: ComponentFixture<InventoryRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
