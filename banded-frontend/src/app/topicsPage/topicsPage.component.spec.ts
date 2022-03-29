import { ComponentFixture, TestBed } from '@angular/core/testing';

import { topicsPageComponent } from './topicsPage.component';

describe('topicsPageComponent', () => {
  let component: topicsPageComponent;
  let fixture: ComponentFixture<topicsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ topicsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(topicsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});