import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from "@angular/material/grid-list";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {AgGridModule} from "ag-grid-angular";
import {FlexLayoutModule} from "@angular/flex-layout";
import {PlayerLandingPage} from "./pages/landing/landing.page";
import {NewGameComponent} from "./components/new-game/new-game.component";
import {ScoreboardComponent} from "./components/scoreboard/scoreboard.component";
import {JoinGameComponent} from "./components/join-game/join-game.component";
import {RouterModule} from "@angular/router";
import {GameModule} from "../Game/game.module";
import {SharedModule} from "../shared/shared.module";
import {SharedService} from "../shared/shared.service";

//https://medium.com/@motcowley/angular-folder-structure-d1809be95542

@NgModule({
  declarations: [
    PlayerLandingPage,
    NewGameComponent,
    JoinGameComponent,
    ScoreboardComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatButtonModule,
    MatGridListModule,
    AgGridModule.withComponents([]),
    FlexLayoutModule,
    RouterModule,
    GameModule,
    SharedModule

  ],
  exports:[PlayerLandingPage],
  providers: [],
  bootstrap: []
})
export class PlayerModule { }
