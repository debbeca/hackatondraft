/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Hackathon8
 *
 */
public class LastMonthFactoryTest {

	private LastMonthFactory factory = new LastMonthFactory();

	@Test
	public void test() {
		Assert.assertEquals("1st May 2017", factory.getMailTradeDate("01/05/2017"));
	}
	
	@Test
	public void getEntity() {
		System.out.println(factory.buildTransactionBlueMix(null, null, null, null, null, null)) ;
	} ;

}
