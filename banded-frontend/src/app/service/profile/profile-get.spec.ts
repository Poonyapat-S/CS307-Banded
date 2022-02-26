import { TestBed } from '@angular/core/testing';

import { ProfileGetResolver } from './profile-get';

describe('ProfileResolverResolver', () => {
  let resolver: ProfileGetResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(ProfileGetResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
