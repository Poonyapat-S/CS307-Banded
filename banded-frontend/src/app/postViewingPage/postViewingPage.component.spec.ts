import { ComponentFixture, TestBed } from '@angular/core/testing';

import { postViewingPageComponent } from './postViewingPage.component';

describe('postViewingPageComponent', () => {
  let component: postViewingPageComponent;
  let fixture: ComponentFixture<postViewingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ postViewingPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(postViewingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
