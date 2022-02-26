import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HttpContextToken,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';

export const BYPASS_UNAUTH = new HttpContextToken(() => false);

@Injectable()

export class UnauthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req);
    // return next.handle(req).pipe(catchError(error => this.handleError(error)));
  }

  handleError(error: HttpErrorResponse): Observable<any> {
      if (error.status == 401) {
          alert("Invalid Credentials");
          this.router.navigate(['login']);
      } 
      return throwError(error);
    }
}

export const UnauthInterceptorProvider = [
  { provide: HTTP_INTERCEPTORS, useClass: UnauthInterceptor, multi: true }
];