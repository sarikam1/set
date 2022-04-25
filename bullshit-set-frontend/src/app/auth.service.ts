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
  token: String = "";
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



  //https://spring.io/guides/tutorials/spring-security-and-angular-js/#_the_login_page_angular_js_and_spring_security_part_ii
  login(credentials: User): Observable<any> {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    //wrap service method in second observable
    //https://stackoverflow.com/questions/49630371/return-a-observable-from-a-subscription-with-rxjs
    return new Observable(subscriber => {
      this.http.get(`${this.apiServerUrl}/api/users/user`, {headers: headers}).subscribe(
        response => {
          console.log(response);
          // @ts-ignore
          if (response['name']) {
            this.incorrectCredentials = false;
            this.authenticated = true;
            sessionStorage.setItem(
              'token',
              btoa(credentials.username + ':' + credentials.password));
            sessionStorage.setItem('currentUser', credentials.username);
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
