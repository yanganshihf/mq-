package com.ruitesen.mq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ruitesen.mq.util.RabbitMQUtil;

/**
 * 测试生产者
 * 
 * @author yanganshi
 *
 */
public class TestProducer {
	public final static String EXCHANGE_NAME = "fanout_exchange";
	
	public static void main(String[] args) throws Exception{
		RabbitMQUtil.checkServer();
		// 创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		// 设置rabbitMQ相关信息
		connectionFactory.setHost("localhost");
		// 创建一个新的连接
		Connection newConnection = connectionFactory.newConnection();
		// 创建一个通道
		Channel createChannel = newConnection.createChannel();
		
		createChannel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		for (int i = 0; i < 100; i++) {
			String message = "direct 消息:"+i;
			
			// 发送消息到队列中
			createChannel.basicPublish(EXCHANGE_NAME,"", null, message.getBytes("UTF-8"));
			System.out.println("发送消息: "+message);
		}
		
		// 关闭通道,连接
		createChannel.close();
		newConnection.close();
	}
}
