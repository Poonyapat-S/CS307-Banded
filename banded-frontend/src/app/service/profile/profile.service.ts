import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay } from 'rxjs/operators';

export class Profile{
  constructor(
    public name:string,
    public userName:string,
    public email:string,
    public bio: string,
    public favBand: string,
    public favSong: string
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private httpClient:HttpClient) { }
  public getProfile() {
    return this.httpClient.get<Profile>("http://localhost:8080/profile").pipe(delay(500));
  }
}
