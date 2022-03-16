import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import {User} from "../shared/models/user";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  credentials: User = { username: "", password: "" };

  constructor(public authService: AuthService) { }

  login(){
    this.authService.login(this.credentials);
  }

  authenticated() {
    return this.authService.authenticated;
  }

}
