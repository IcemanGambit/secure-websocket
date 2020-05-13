package com.aishwary.securewebsocket.service;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.aishwary.securewebsocket.model.ReplyModel;

public class CustomSessionHandler extends StompSessionHandlerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(CustomSessionHandler.class);

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		ReplyModel msg = (ReplyModel) payload;
	    logger.info("Received : " + msg.getReply() + " from : " + msg.getName());
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		session.subscribe("/topic/reply", this);
		logger.info("Subscribed to " + session.getSessionId());
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return ReplyModel.class;
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		logger.error("Exception", exception);
	}
	
}
