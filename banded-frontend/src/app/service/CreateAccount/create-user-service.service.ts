import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
// import { throws } from 'assert';

export class NewUser{
  constructor(
    public name:string,
    public username:string,
    public password:string,
    public email:string,
    public confPassword:string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class CreateUserService {

  constructor( private httpClient:HttpClient) { }

  public createUser(user: NewUser) {
    return this.httpClient.post("http://localhost:8080/api/v1/registration", user, {responseType: 'text'});
  }
}
