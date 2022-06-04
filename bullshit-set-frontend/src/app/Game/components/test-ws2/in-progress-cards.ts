import {ChangeDetectorRef, Component, ElementRef, HostListener, Input, NgZone, ViewChild} from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';


@Component({
  selector: 'app-in-progress-cards',
  templateUrl: './in-progress-cards.html',
  styleUrls: ['./in-progress-cards.css']
})
export class inProgressCards {

  greetings: string[] = [];
  disabled = true;
  name: string | undefined;
  private stompClient: any;
  participantList: string[] =  JSON.parse(localStorage.getItem('gameParticipantList') || "[]");
  private input: any;
  disconnectDisabled = false;
  leaveMessage: string = "";
  username = sessionStorage.getItem('currentUser');


  constructor() {


    const stompSubscribe = (stompClient: any, endpoint: string, callback: (data: any) => void) => {
      stompClient.subscribe(endpoint, callback);
      return stompClient;
    }

    //https://stackoverflow.com/questions/56204346/typescript-error-an-outer-value-of-this-is-shadowed-by-this-container
    const connect = () => {
      return new Promise((resolve, reject) => {
        const socket = new SockJS('http://localhost:8080/gs-guide-websocket');
         let stompClient = Stomp.over(socket);
        //headers, connectCallback
        stompClient.connect({}, (frame) => {
            this.stompClient = stompClient;
            resolve(stompClient)
          })
      })
    }

    const addToParticipantList = (participants: string[]) => {
      localStorage.setItem('gameParticipantList', JSON.stringify(participants));
      this.participantList = participants;
      return this.participantList;
    }

    const leaveMessage = (message: string) => {
      this.leaveMessage = message;
    }


    window.addEventListener('load', () => {
      let username = sessionStorage.getItem('currentUser');
      let disconnectButton = document.getElementById("webchat_disconnect");

      connect()
        .then((stompClient) => stompSubscribe(stompClient,'/queue/newMember-'+username, (data: { body: string }) => {
          console.log("inside callback newMember");
          console.log(data);
          let res = addToParticipantList(JSON.parse(data.body));
          if (res.length > 0) {
            console.log("participantList is " + res);
          } else {
            console.log("participantList is empty");
          }
        }))
        .then((stompClient) => stompSubscribe(stompClient, '/topic/leftMember', (data) => {
          console.log("inside callback leftMember");
          console.log(data);
          leaveMessage(data);
        }))
        .then((stompClient) => this.stompClientSendMessage(stompClient, '/stream/joinGame', username))
        // .then((stompClient) => disconnectButton!.addEventListener('click', () =>
        //   stompClientSendMessage(stompClient, '/stream/leaveGame', username)))

    });

    window.addEventListener('beforeunload', this.leaveGame.bind(this));


  }


  stompClientSendMessage(stompClient: any, endpoint: string, message: string | null) { // 9
    stompClient.send(endpoint, {}, message);
    this.stompClient = stompClient;
    return stompClient;
  }

  leaveGame() {
    this.stompClientSendMessage(this.stompClient, '/stream/leaveGame', this.username);
    sessionStorage.removeItem('currentGameId');

    this.disconnect();
  }


  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
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
      JSON.stringify({'name': this.name})
    );
  }

  showGreeting(message: string) {
    this.greetings.push(message);
    console.log("Greetings are: " + this.greetings)
  }


}
