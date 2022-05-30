import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookFLightComponent } from './book-flight.component';

describe('BookFLightComponent', () => {
  let component: BookFLightComponent;
  let fixture: ComponentFixture<BookFLightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookFLightComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookFLightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
