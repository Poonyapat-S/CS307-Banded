import { ComponentFixture, TestBed } from '@angular/core/testing';

import { createPostComponent } from './createPost.component';

describe('createPostComponent', () => {
  let component: createPostComponent;
  let fixture: ComponentFixture<createPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ createPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(createPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
