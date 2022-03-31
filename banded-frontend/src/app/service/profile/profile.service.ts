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

  public getUserProfile(userName: string) {
    return this.httpClient.get<Profile>("http://localhost:8080/profile/"+userName).pipe(delay(500));
  }

  public editBio(userName:string, text: string) {
    console.log("http://localhost:8080/profile/editbio/"+userName);
    console.log(text);
    return this.httpClient.put<any>("http://localhost:8080/profile/editbio/"+userName, text);
  }

  public deleteProfile(userName: string) {
    return this.httpClient.delete<any>("http://localhost:8080/profile/delete/"+userName);
  }

  public followUser(userName: string) {
    return this.httpClient.post("http://localhost:8080/api/followcontrol/followuser", userName);
  }

  public unfollowUser(userName: string) {
    return this.httpClient.post("http://localhost:8080/api/followcontrol/unfollowuser", userName);
  }

  public getFollowedUserNames() {
    return this.httpClient.get<String[]>("http://localhost:8080/api/followcontrol/getfollowedusers");
  }
}
