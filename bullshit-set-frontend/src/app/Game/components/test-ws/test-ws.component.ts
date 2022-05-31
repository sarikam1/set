import {ChangeDetectorRef, Component, Input, NgZone} from '@angular/core';
// import {WebSocketAPI} from "../../../shared/WebSocketAPI";
import {Subject} from "rxjs";
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';



@Component({
  selector: 'app-test-ws',
  templateUrl: './test-ws.component.html',
  styleUrls: ['./test-ws.component.css']
})
export class testWsComponent {


  greetings: string[] = [];
  disabled = true;
  name: string | undefined;
  private stompClient: any;

  constructor() { }

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    const socket = new SockJS('http://localhost:8080/gs-guide-websocket');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame: string) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/greetings', function (hello: { body: string; }) {
        _this.showGreeting(JSON.parse(hello.body).content);
      });
    });
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  sendName() {
    this.stompClient.send(
      "/stream/hello",
      {},
      JSON.stringify({ 'name': this.name })
    );
  }

  showGreeting(message: string) {
    this.greetings.push(message);
    console.log("Greetings are: " + this.greetings)
  }
}
