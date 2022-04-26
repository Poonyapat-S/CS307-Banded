import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FollowingTopicComponent } from './following-topic.component';

describe('FollowingTopicComponent', () => {
  let component: FollowingTopicComponent;
  let fixture: ComponentFixture<FollowingTopicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FollowingTopicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FollowingTopicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
