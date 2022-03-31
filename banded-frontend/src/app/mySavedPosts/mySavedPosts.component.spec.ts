import { ComponentFixture, TestBed } from '@angular/core/testing';

import { mySavedPostsComponent } from './mySavedPosts.component';

describe('mySavedPostsComponent', () => {
  let component: mySavedPostsComponent;
  let fixture: ComponentFixture<mySavedPostsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ mySavedPostsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(mySavedPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});