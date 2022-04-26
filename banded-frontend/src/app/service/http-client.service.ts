import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
// import { throws } from 'assert';

export class User{
  constructor(
    public name:string,
    public userName:string,
    public password:string,
    public email:string,
    public confPassword:string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor( private httpClient:HttpClient) { }

  getUsers() {
    console.log("test call");
    return this.httpClient.get<User[]>(environment.API_URL + '/api/user/allusers');
  }

  public deleteUsers(user: User) {
    return this.httpClient.delete<User>(environment.API_URL + "/users/"+user.userName);
  }

  public createUser(user: User) {
    return this.httpClient.post(environment.API_URL+"/api/v1/registration", user, {responseType: 'text'});
  }
}
