/**
 * 
 */
package com.softeam.hackathon.batch.reader;

import org.junit.Test;

import com.softeam.hackathon.batch.factory.MailContentGenerator;

/**
 * @author Hackathon8
 *
 */
public class MailGeneratorReaderTest {

	@Test
	public void test() {
		MailContentGenerator gen = new MailContentGenerator();
		// mailContentList.addAll(gen.getLastMonthByProduct("PRODUIT1"));
		gen.getOneDateByProduct("PRODUIT1", 5).forEach(System.out::println);
	}

}
