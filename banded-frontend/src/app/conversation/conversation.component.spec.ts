import { ComponentFixture, TestBed } from '@angular/core/testing';

import {conversationComponent} from './conversation.component';

describe('conversationComponent', () => {
  let component: conversationComponent;
  let fixture: ComponentFixture<conversationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ conversationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(conversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
