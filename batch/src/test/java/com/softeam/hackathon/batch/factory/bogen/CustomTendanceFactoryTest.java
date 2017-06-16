/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen;

import org.junit.Test;

/**
 * @author Hackathon8
 *
 */
public class CustomTendanceFactoryTest {

	private CustomTendanceFactory factory = new CustomTendanceFactory();

	@Test
	public void test() {
		factory.getTransactionFacebook().forEach(System.out::println);
		factory.getTransactionGoogle().forEach(System.out::println);
	}

}
