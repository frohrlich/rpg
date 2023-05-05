package com.projet.rpg.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// destination for messages to the clients
		config.enableSimpleBroker("/topic");
		// destination for messages from the clients (game?)
		config.setApplicationDestinationPrefixes("/game");
	}

	// this method uses fallbacks in case of websockets not available
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
	}
}
