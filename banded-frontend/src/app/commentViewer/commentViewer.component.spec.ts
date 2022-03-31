import { ComponentFixture, TestBed } from '@angular/core/testing';

import { commentViewerComponent } from './commentViewer.component';

describe('commentViewerComponent', () => {
  let component: commentViewerComponent;
  let fixture: ComponentFixture<commentViewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ commentViewerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(commentViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
