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
// import {GameComponent} from "./game.component";
import {RouterModule} from "@angular/router";
import {SharedService} from "../shared/shared.service";
import {SharedModule} from "../shared/shared.module";
import {JoinedSoFarComponent} from "./components/joined-so-far/joined-so-far.component";
import {testWsComponent} from "./components/test-ws/test-ws.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [

    WaitingPage,
    InProgessPage,
    JoinedSoFarComponent,
    testWsComponent,
    InProgessPage
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
    SharedModule,
    FormsModule

  ],
  providers: [],
  bootstrap: []
})
export class GameModule { }
