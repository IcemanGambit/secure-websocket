package com.aishwary.securewebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.aishwary.securewebsocket.model.MessageModel;
import com.aishwary.securewebsocket.model.ReplyModel;

@Controller
public class MessageController {
	
	@MessageMapping("/send")
	@SendTo("/topic/reply")
	public ReplyModel sendMessage(MessageModel message) {
		return new ReplyModel(message.getName(), "Hello there");
	}
}
