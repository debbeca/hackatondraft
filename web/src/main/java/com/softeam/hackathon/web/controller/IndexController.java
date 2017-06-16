package com.softeam.hackathon.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController implements ErrorController{
	
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final String PATH = "/error";
	

	    @RequestMapping(value = PATH)
	    public String error() {
	        return "index";
	    }

	    @Override
	    public String getErrorPath() {
	        return PATH;
	    }
	    
	    
	@RequestMapping("/")
	public String index(ModelMap model) {
		log.info("Hackaton Started....");
		return "index";
	}
	

}
	
