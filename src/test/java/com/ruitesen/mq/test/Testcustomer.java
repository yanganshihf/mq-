package com.ruitesen.mq.test;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.ruitesen.mq.util.RabbitMQUtil;

import cn.hutool.core.util.RandomUtil;

/**
 * 消息接收者
 * 
 * @author yanganshi
 *
 */
public class Testcustomer {
	public final static String EXCHANGE_NAME = "fanout_exchange";
	
	public static void main(String[] args) throws Exception{
		// 为当前消费者随机取名
		String name = "customer-"+RandomUtil.randomString(5);
		
		// 判断服务器是否启动
		RabbitMQUtil.checkServer();
		
		// 创建工厂连接
		ConnectionFactory connectionFactory = new ConnectionFactory();
		// 设置rabbitMQ的地址
		connectionFactory.setHost("localhost");
		// 创建一个新的连接
		Connection newConnection = connectionFactory.newConnection();
		// 创建一个新通道
		Channel createChannel = newConnection.createChannel();
		// 交换机声明 交换机名称 交换机类型
		createChannel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		// 获取一个临时队列
		String queueName = createChannel.queueDeclare().getQueue();
		// 队列与交换机绑定参数:队列名称,交换机名称,routingKey
		createChannel.queueBind(queueName, EXCHANGE_NAME, "");
		
		System.out.println(name+" 等待接收消息....");
		
		// 通过传入一个通道
		// 告诉服务器我们需要哪个频道的消息'
		// 如果通道中有消息,就执行回调函数 handleDelivery
		
		DefaultConsumer defaultConsumer = new DefaultConsumer(createChannel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body)  throws IOException {
				String message = new String(body, "UTF-8");
                System.out.println(" 接收到消息 '" + message + "'");
			}
		};
		//自动回复队列应答 -- RabbitMQ中的消息确认机制
		createChannel.basicConsume(queueName, true, defaultConsumer);
	}
}
