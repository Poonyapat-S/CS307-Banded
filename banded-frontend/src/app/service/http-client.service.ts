import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { throws } from 'assert';

export class User{
  constructor(
    public userID:string,
    public name:string,
    public userName:string,
    public password:string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor( private httpClient:HttpClient) { }

  getUsers() {
    console.log("test call");
    return this.httpClient.get<User[]>('http://localhost:8080/users/');
  }

  public deleteUsers(user: User) {
    return this.httpClient.delete<User>("http://localhost:8080/users/"+user.userID);
  }

  public createUser(user: User) {
    return this.httpClient.post<User>("http://localhost:8080/users", user);
  }
}
