import { Injectable }     from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import { Router } from '@angular/router';
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean>|Promise<boolean>|boolean {
    //https://stackoverflow.com/questions/37364973/what-is-the-difference-between-promises-and-observables
    if (!sessionStorage.getItem('currentUser')) {
      this.router.navigate(['app/login']);
    }
    return true;
  }
}
