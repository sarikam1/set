import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {NewGameComponent} from "./player/components/new-game/new-game.component";
import {MatGridListModule} from "@angular/material/grid-list";
import {JoinGameComponent} from "./player/components/join-game/join-game.component";
import {ScoreboardComponent} from "./player/components/scoreboard/scoreboard.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {AgGridModule} from "ag-grid-angular";
import {FlexLayoutModule} from "@angular/flex-layout";
import {AppRoutingModule} from "./app-routing.module";
import {PlayerLandingPage} from "./player/pages/landing/landing.page";
import {PlayerModule} from "./player/player.module";
import { RouterModule } from '@angular/router';
import {SharedService} from "./shared/shared.service";
import {LoginComponent} from "./login/login.component";
import {FormsModule} from "@angular/forms";


//https://medium.com/@motcowley/angular-folder-structure-d1809be95542

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [PlayerModule, RouterModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
