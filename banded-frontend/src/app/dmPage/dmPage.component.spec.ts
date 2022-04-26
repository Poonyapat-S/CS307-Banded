import { ComponentFixture, TestBed } from '@angular/core/testing';

import { dmPageComponent} from './dmPage.component';

describe('dmPageComponent', () => {
  let component: dmPageComponent;
  let fixture: ComponentFixture<dmPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ dmPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(dmPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
