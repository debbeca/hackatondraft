package com.example.hackaton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.hackaton.model.Conversation;
import com.example.hackaton.model.UserInput;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@RestController
@RequestMapping("api")
public class ConversationController {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String CONVERSATION_USERNAME = "595a07f8-6df6-4406-81d9-192ff8e75c0b";
	private static final String CONVERSATION_PASS = "CXyhnV41v4cy";
	private static final String CONVERSATION_WORKSPACE = "e484768c-14ad-4c61-8c7f-9ebca65ad276";

	@RequestMapping(value="conversation", method = RequestMethod.POST)
	Conversation respondToClient(@RequestBody UserInput input){
			log.info("##### :  "+ input.getQuery());
			Conversation conversation = new Conversation();
//            response = service.message(CONVERSATION_WORKSPACE, request).execute();
            ConversationService service = new ConversationService("2017-06-14", CONVERSATION_USERNAME, CONVERSATION_PASS);
            MessageRequest  request = new MessageRequest.Builder().inputText(input.getQuery()).build();
            MessageResponse response = service.message(CONVERSATION_WORKSPACE, request).execute();
            conversation.setText(response.getText().get(0));
			return conversation; 
	}
	
}
