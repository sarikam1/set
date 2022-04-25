import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WaitingPage} from "./Game/pages/waiting/waiting.page";
import {InProgessPage} from "./Game/pages/in-progress/in-progess.page";
import {PlayerLandingPage} from "./player/pages/landing/landing.page";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "./auth-guard.service";


const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'app', component: LoginComponent},
  { path: 'app/login', component: LoginComponent},
  { path: 'app/player-landing', component: PlayerLandingPage, canActivate:[AuthGuardService]},
  { path: 'app/waiting', component: WaitingPage, canActivate:[AuthGuardService]},
  { path: 'app/game/create', component: WaitingPage, canActivate:[AuthGuardService]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuardService]
})
export class AppRoutingModule { }
