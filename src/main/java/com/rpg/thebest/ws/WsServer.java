package com.rpg.thebest.ws;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;

import com.rpg.thebest.Game;

@ServerEndpoint("/websocketendpoint")
public class WsServer {
	private Game myGame; // contains current game informations

	@OnOpen
	public void onOpen() {
		System.out.println("Open Connection ...");

		myGame = new Game(); // starts game

		System.out.println(myGame.getCurrentVue());
	}

	@OnClose
	public void onClose() {
		System.out.println("Close Connection ...");
	}

	@OnMessage
	public String onMessage(String message) {
		System.out.println("Message from the client: " + message);
		String echoMsg = "";

		// updates game and sends update message to the client
		echoMsg = myGame.update(message);
		
		System.out.println("Echo from the server : " + echoMsg);
		System.out.println();
		return echoMsg;
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}

}
