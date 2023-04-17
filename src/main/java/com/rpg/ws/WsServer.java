package com.rpg.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import com.rpg.vue.Option;
import com.rpg.vue.Personnage;
import com.rpg.vue.Vue;
import com.rpg.vue.VueDialogue;

@ServerEndpoint("/websocketendpoint")
public class WsServer {
	Vue vueActuelle;

	@OnOpen
	public void onOpen() {
		System.out.println("Open Connection ...");

		// View initialization
		Personnage felix = new Personnage("Félix");
		Personnage inconnu = new Personnage("Inconnu");
		String texteVue = "Bienvenue dans la forêt des sangliers, voyageur.";
		this.vueActuelle = new VueDialogue("img/bg_foret", texteVue, felix, inconnu);
		this.vueActuelle.addOption(new Option("Saluer l'inconnu"));
		this.vueActuelle.addOption(new Option("Ramasser une branche et le frapper"));
		this.vueActuelle.addOption(new Option("S'enfuir en courant"));
	}

	@OnClose
	public void onClose() {
		System.out.println("Close Connection ...");
	}

	@OnMessage
	public String onMessage(String message) {
		System.out.println("Message from the client: " + message);
		String echoMsg = "";
		if (message.equals("getOptionTexte")) {
			echoMsg = this.vueActuelle.getOptions().get(0).getTexte();
		}
		System.out.println("Echo from the server : " + echoMsg);
		System.out.println();
		return echoMsg;
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
}
