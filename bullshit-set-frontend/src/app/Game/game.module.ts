import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {MatButtonModule} from "@angular/material/button";
import {MatGridListModule} from "@angular/material/grid-list";
import {FlexLayoutModule} from "@angular/flex-layout";
import {AgGridModule} from "ag-grid-angular";
import {WaitingPage} from "./pages/waiting/waiting.page";
import {InProgessPage} from "./pages/in-progress/in-progess.page";

@NgModule({
  declarations: [
    WaitingPage,
    InProgessPage
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
  bootstrap: []
})
export class GameModule { }
