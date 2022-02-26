import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Profile, ProfileService } from './profile.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileGetResolver implements Resolve<Profile> {
constructor (private profileService: ProfileService) {}
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Profile> {
    return this.profileService.getProfile();
  }
}
