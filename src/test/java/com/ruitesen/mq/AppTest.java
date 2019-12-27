package com.ruitesen.mq;

import org.junit.Test;

import com.ruitesen.mq.test.TestProducer;
import com.ruitesen.mq.test.Testcustomer;

/**
 * 测试单元
 * 
 * @author yanganshi
 */
public class AppTest {
	String[] args;
	
	@Test
 	public void test() throws Exception {
		System.out.println("启用测试单元...");
		// 建立两个消息接收者
		Testcustomer.main(args);
		Testcustomer.main(args);
		// 生产消息
		TestProducer.main(args);
	}
}
