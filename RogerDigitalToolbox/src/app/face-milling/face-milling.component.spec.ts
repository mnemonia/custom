import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FaceMillingComponent } from './face-milling.component';

describe('FaceMillingComponent', () => {
  let component: FaceMillingComponent;
  let fixture: ComponentFixture<FaceMillingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FaceMillingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FaceMillingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
