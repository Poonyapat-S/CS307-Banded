import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BYPASS_UNAUTH } from 'src/app/_helper/unauth.interceptor';

export class Credentials {
  constructor(
  public username: string,
  public password: string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})


export class AuthenticationService {

  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  constructor(private http: HttpClient) {

  }

  login(credentials: Credentials): Observable<any>  {
    return this.http.post(`http://localhost:8080/auth/login`, credentials);
  }
}