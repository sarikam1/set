import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WaitingPage} from "./Game/pages/waiting/waiting.page";
import {InProgessPage} from "./Game/pages/in-progress/in-progess.page";
import {PlayerLandingPage} from "./player/pages/landing/landing.page";
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  { path: '', component: PlayerLandingPage},
  { path: 'app', component: PlayerLandingPage},
  { path: 'app/player-landing', component: PlayerLandingPage},
  { path: 'app/waiting', component: WaitingPage},
  { path: 'app/login', component: LoginComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
