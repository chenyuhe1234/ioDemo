package com.chenyuhe.cn.bio.single;

import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class BIOSingleClient {
	
	
	public static void main(String[] args) throws Exception {

		// 要和谁进行通信 端口是多少
		Socket client = new Socket("127.0.0.1",8080);
		// 向socket写数据
		OutputStream out = client.getOutputStream();

		// 生成一个随机的UUID
		String name = UUID.randomUUID().toString() + "chenyuhe";

		System.out.println("客户端发送的数据是:" + name);
		out.write(name.getBytes());
		out.close();
		client.close();



	}
}