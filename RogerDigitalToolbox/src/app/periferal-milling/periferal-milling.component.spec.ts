import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriferalMillingComponent } from './periferal-milling.component';

describe('PeriferalMillingComponent', () => {
  let component: PeriferalMillingComponent;
  let fixture: ComponentFixture<PeriferalMillingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeriferalMillingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeriferalMillingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
