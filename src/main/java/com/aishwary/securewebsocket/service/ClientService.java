package com.aishwary.securewebsocket.service;

import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.aishwary.securewebsocket.model.MessageModel;

@Service
public class ClientService {
	
	StompSession session;
	
	String url = "ws://localhost:8080/send";
	
	public void connectToWebScket() throws InterruptedException, ExecutionException {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		StompSessionHandler sessionHandler = new CustomSessionHandler();
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		session = stompClient.connect(url, sessionHandler).get();
	}
	
	public void sendmessage(MessageModel message) {
		session.send("/app/send", message);
	}
}
