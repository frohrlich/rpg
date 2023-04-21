package com.rpg.ws;

import com.rpg.entities.AllieEntity;
import com.rpg.entities.JoueurEntity;
import com.rpg.entities.PersonnageEntity;
import com.rpg.entities.Role;
import com.rpg.entities.Sexe;
import com.rpg.vue.Option;
import com.rpg.vue.Vue;
import com.rpg.vue.VueDialogue;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocketendpoint")
public class WsServer {
	Vue vueActuelle;

	@OnOpen
	public void onOpen() {
		System.out.println("Open Connection ...");

		// View initialization
		PersonnageEntity felix = new JoueurEntity(2, 3, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3,
				1000);
		PersonnageEntity fermiere = new AllieEntity(2, 3, "Basma", Sexe.F, Role.Ar, 150, 1280, 2280, 300, 300, 2000, 3);
		String texteVue = "Bienvenue dans la forêt des sangliers, voyageur.";
		Vue myVue = new VueDialogue("img/bg_foret", texteVue, felix, fermiere);
		myVue.addOption(new Option("Saluer l'inconnue bien bas"));
		myVue.addOption(new Option("Ramasser une branche et la frapper"));
		myVue.addOption(new Option("S'enfuir en courant"));
		this.vueActuelle = myVue;
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
