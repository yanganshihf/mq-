package com.ruitesen.mq;

import org.junit.Test;

import com.ruitesen.mq.test.TestProducer;
import com.ruitesen.mq.test.Testcustomer;

/**
 * Unit test for simple App.
 */
public class AppTest {
	String[] args;
	
	@Test
 	public void test() throws Exception {
		System.out.println("启用测试单元...");
		Testcustomer.main(args);
		Testcustomer.main(args);
		TestProducer.main(args);
	}
}
