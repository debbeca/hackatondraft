package com.softeam.hackathon.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softeam.hackathon.web.model.Conversation;
import com.softeam.hackathon.web.model.ConversationEntity;
import com.softeam.hackathon.web.model.Intention;
import com.softeam.hackathon.web.model.Intervalle;
import com.softeam.hackathon.web.model.UserInput;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.Entity;
import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;


@RestController
@RequestMapping("api")
public class ConversationController {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String CONVERSATION_USERNAME = "3eecacb2-c92d-45df-84f8-ddfcc62b258d";
	private static final String CONVERSATION_PASS = "CnXd0nG1aJ4F";
	private static final String CONVERSATION_WORKSPACE = "a1e46159-c29d-4551-8c13-b046cb9d4a9c";

	@RequestMapping(value="conversation", method = RequestMethod.POST)
	Conversation respondToClient(@RequestBody UserInput input){
			log.info("##### :  "+ input.getQuery());
			Conversation conversation = new Conversation();
//            response = service.message(CONVERSATION_WORKSPACE, request).execute();
            ConversationService service = new ConversationService("2017-06-14", CONVERSATION_USERNAME, CONVERSATION_PASS);
            MessageRequest  request = new MessageRequest.Builder().inputText(input.getQuery()).build();
            MessageResponse response = service.message(CONVERSATION_WORKSPACE, request).execute();
            conversation.setText(response.getText().get(0));
            double maxConfidence = -1; 
            Intention maxIntent = null;		
            for (Intent intent : response.getIntents()) {
            	if(intent.getConfidence() > maxConfidence){
            		maxConfidence = intent.getConfidence();
            		log.info("##MAX INTENT### :  "+ intent.getIntent() );
            		maxIntent =  Intention.fromString(intent.getIntent()); 
            	}
			}
            
            
            for ( Entity entity : response.getEntities()){
            	log.info("##Entity### :  "+ entity.getEntity() +"  "+ entity.getValue());
            	switch (ConversationEntity.fromString(entity.getEntity())) {
				case INTERVALLE:
					conversation.setIntervalle(Intervalle.fromString(entity.getValue()));
					break;
				case APPORTEUR:	
					conversation.setApporteur(entity.getValue());
					break;
				default:
					break;
				}
            }
            
            
            conversation.setIntention(maxIntent);
            return conversation; 
	}
	
}
