import { ComponentFixture, TestBed } from '@angular/core/testing';

import { followedUsersComponent} from './followedUsers.component';

describe('followedUsersComponent', () => {
  let component: followedUsersComponent;
  let fixture: ComponentFixture<followedUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ followedUsersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(followedUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
