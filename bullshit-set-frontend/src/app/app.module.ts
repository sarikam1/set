import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {NewGameComponent} from "./components/new-game/new-game.component";
import {MatGridListModule} from "@angular/material/grid-list";
import {JoinGameComponent} from "./components/join-game/join-game.component";
import {ScoreboardComponent} from "./components/scoreboard/scoreboard.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {AgGridModule} from "ag-grid-angular";
import {FlexLayoutModule} from "@angular/flex-layout";


@NgModule({
  declarations: [
    AppComponent,
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
    FlexLayoutModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
