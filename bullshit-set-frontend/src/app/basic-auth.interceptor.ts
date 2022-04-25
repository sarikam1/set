import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class MyBasicAuthInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with basic auth credentials if available
    console.log("intercepting!");
    let currentUser = sessionStorage.getItem('currentUser');
    let authData = sessionStorage.getItem('token');
    if (currentUser && authData) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${authData}`
        }
      });
    }

    return next.handle(request);
  }
}
