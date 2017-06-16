package com.softeam.hackathon.batch.reader;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.softeam.hackathon.batch.factory.MailContentGenerator;

public class MailGeneratorReader implements ItemReader<String> {

	private List<String> mailContentList = new ArrayList<>();

	private Integer mailCt = 0;

	@PostConstruct
	private void init() {
		MailContentGenerator gen = new MailContentGenerator();
		// mailContentList.addAll(gen.getLastMonthByProduct("PRODUIT1"));
		// mailContentList.addAll(gen.getOneDateByProduct("PRODUIT1",5));
		mailContentList.addAll(gen.getTransactionFacebook());
		mailContentList.addAll(gen.getTransactionGoogle());
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		String mailContent = null;

		if (mailCt < mailContentList.size()) {
			mailContent = mailContentList.get(mailCt++);
		}

		return mailContent;
	}
}
