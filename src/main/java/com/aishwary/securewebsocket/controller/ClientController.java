package com.aishwary.securewebsocket.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aishwary.securewebsocket.model.MessageModel;
import com.aishwary.securewebsocket.service.ClientService;

@RestController
public class ClientController {
	@Autowired
	public ClientService clientService;
	
	@RequestMapping(value="/connect",method = RequestMethod.GET)
	public void connect() {
		try {
			clientService.connectToWebScket();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/sendmsg",method = RequestMethod.POST)
	public void sendMessage(@RequestBody MessageModel message) {
		clientService.sendmessage(message);	}
}
