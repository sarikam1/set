import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import {User} from "../shared/models/user";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: User = { username: "", password: "" };

  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit() {
    sessionStorage.setItem('token', '');
    sessionStorage.setItem('currentUser', '');
    console.log(sessionStorage.getItem('token'), sessionStorage.getItem('currentUser'));
  }

  login(){
    console.log("logging in!")
    //asynchronous function: https://stackoverflow.com/questions/52115904/how-to-call-a-function-after-the-termination-of-another-function-in-angular/52116252
    this.authService.login(this.credentials).subscribe(
      () =>
        this.redirectAfterlogin()
    )
  }

  authenticated() {
    //console.log(this.authService.authenticated)
    return this.authService.authenticated;
  }

  incorrectCredentials() {
    return this.authService.incorrectCredentials;
  }

  redirectAfterlogin(){
    console.log("redirection function")
    console.log(sessionStorage.getItem('token'), sessionStorage.getItem('currentUser'));
    if(this.authService.authenticated) {
      console.log("Redirecting!")
      this.router.navigate(['app/player-landing'])
    }
  }



}
