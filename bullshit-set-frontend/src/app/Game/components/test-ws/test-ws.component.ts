import { Component } from '@angular/core';
import {WebSocketAPI} from "../../../shared/WebSocketAPI";


@Component({
  selector: 'app-test-ws',
  templateUrl: './test-ws.component.html',
  styleUrls: ['./test-ws.component.css']
})
export class testWsComponent {
  title = 'bullshit-set';

  webSocketAPI: WebSocketAPI | undefined;
  greeting: any;
  name: string = "";
  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new testWsComponent());
  }

  connect(){
    this.webSocketAPI!._connect();
  }

  disconnect(){
    this.webSocketAPI!._disconnect();
  }

  sendMessage(){
    this.webSocketAPI!._send(this.name);
  }

  handleMessage(message: any){
    this.greeting = message;
  }
}
