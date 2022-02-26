import { TestBed } from '@angular/core/testing';

import { UnauthInterceptor } from './unauth.interceptor';

describe('UnauthInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      UnauthInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: UnauthInterceptor = TestBed.inject(UnauthInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
