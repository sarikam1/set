import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WaitingPage} from "./Game/pages/waiting/waiting.page";
import {InProgessPage} from "./Game/pages/in-progress/in-progess.page";
import {PlayerLandingPage} from "./player/pages/landing/landing.page";


const routes: Routes = [
  { path: '', component: PlayerLandingPage},
  { path: 'player-landing', component: PlayerLandingPage},
  { path: 'waiting', component: WaitingPage}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
