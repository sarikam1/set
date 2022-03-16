import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {User} from "./shared/models/user";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = environment.apiBaseUrl;
  authenticated: boolean = false;
  authenticatedUserName: String = "";

  constructor(private http: HttpClient) { }
  //
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
  login(credentials: User) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)} : {});

    this.http.get('/api/users/user', {headers: headers}).subscribe(response => {
      // @ts-ignore
      if (response['name']) {
        this.authenticated = true;
        // @ts-ignore
        this.authenticatedUserName = response['name'];
      } else {
        this.authenticated = false;
      }
    });
  }
}