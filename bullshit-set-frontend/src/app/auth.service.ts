import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from "./shared/models/user";
import {environment} from "../environments/environment";
import {BehaviorSubject, Observable, of, subscribeOn} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = environment.apiBaseUrl;
  authenticated: boolean = false;
  authenticatedUserName: String = "";
  incorrectCredentials: boolean = false;

  constructor(private http: HttpClient) {
  }

  // register(userDto: User){
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json'
  //     })
  //   };
  //
  //   this.http.post("api/v1/saveUser", userDto, httpOptions).subscribe(() => {
  //     this.login(userDto);
  //   });
  // }

  // getData(): Observable<any> {
  //   return new Observable(subscriber => {
  //     this.http.get(url)
  //       .pipe(catchError(this.handleError)
  //         .subscribe(res => {
  //           // Do my service.ts logic.
  //           // ...
  //           subscriber.next(res)
  //           subscriber.complete()
  //         }, err => subscriber.error(err))
  //   })
  // }

  //https://spring.io/guides/tutorials/spring-security-and-angular-js/#_the_login_page_angular_js_and_spring_security_part_ii
  login(credentials: User) : Observable<any> {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    //asynchronous method call!
    //https://stackoverflow.com/questions/49630371/return-a-observable-from-a-subscription-with-rxjs
    return new Observable(subscriber => {
      this.http.get(`${this.apiServerUrl}/api/users/user`, {headers: headers}).subscribe(
      response => {
        console.log(response);
        // @ts-ignore
        if (response['name']) {
          this.incorrectCredentials = false;
          this.authenticated = true;
          // @ts-ignore
          this.authenticatedUserName = response['principal']['player']['userName'];
          console.log("username is" + this.authenticatedUserName);
          subscriber.next(true)
          subscriber.complete()
        }
      },
      err => {
        if (err.status == 403) {
          console.log("incorrect credentials");
          this.incorrectCredentials = true;
          subscriber.error()
        }
      })
      })
  }
}
