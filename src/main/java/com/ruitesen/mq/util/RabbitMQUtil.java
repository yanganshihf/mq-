package com.ruitesen.mq.util;

import javax.swing.JOptionPane;

import cn.hutool.core.util.NetUtil;

/**
 * 判断服务器是否启动
 * 
 * @author yanganshi
 *
 */
public class RabbitMQUtil {
	public static void main(String[] args) {
		checkServer();
	}
	// 检查服务
	public static void checkServer() {
		// 检查端口是否使用
		if(NetUtil.isUsableLocalPort(15672)) {
			JOptionPane.showMessageDialog(null, "RabbitMQ 服务未启动");
			System.exit(1);
		}
	}
}
