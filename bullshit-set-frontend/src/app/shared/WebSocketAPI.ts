// import * as Stomp from 'stompjs';
// import * as SockJS from 'sockjs-client';
// import {AppComponent} from "../app.component";
// import {testWsComponent} from "../Game/components/test-ws/test-ws.component";
//
// export class WebSocketAPI {
//   webSocketEndPoint: string = 'http://localhost:8080/api/ws';
//   topic: string = "/topic/greetings";
//   stompClient: any;
//   testWs: testWsComponent;
//
//   constructor(testWs: testWsComponent){
//     this.testWs = testWs;
//   }
//   _connect() {
//     console.log("Initialize WebSocket Connection");
//     let ws = new SockJS('http://localhost:8080/gs-guide-websocket', null, {timeout: 15000});
//     this.stompClient = Stomp.over(ws);
//     console.log("stompClient is" + this.stompClient);
//     const _this = this;
//     _this.stompClient.connect({}, function (frame: any) {
//       _this.stompClient.subscribe(_this.topic, function (sdkEvent: any) {
//         _this.onMessageReceived(sdkEvent);
//       });
//       //_this.stompClient.reconnect_delay = 2000;
//     }, this.errorCallBack);
//   };
//
//   _disconnect() {
//     if (this.stompClient !== null) {
//       this.stompClient.disconnect();
//     }
//     console.log("Disconnected");
//   }
//
//   // on error, schedule a reconnection attempt
//   errorCallBack(error: string) {
//     console.log("errorCallBack -> " + error)
//     setTimeout(() => {
//       this._connect();
//     }, 5000);
//   }
//
//   /**
//    * Send message to sever via web socket
//    * @param {*} message
//    */
//   _send(message: any) {
//     console.log("calling logout api via web socket");
//     this.stompClient.send("/stream/hello", {}, JSON.stringify(message));
//   }
//
//   onMessageReceived(message: any) {
//     console.log("Message Recieved from Server :: " + message);
//     this.testWs.handleMessage(JSON.parse(message.body).content);
//   }
// }
