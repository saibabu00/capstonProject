import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BowlerlistComponent } from './bowlerlist.component';

describe('BowlerlistComponent', () => {
  let component: BowlerlistComponent;
  let fixture: ComponentFixture<BowlerlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BowlerlistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BowlerlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
