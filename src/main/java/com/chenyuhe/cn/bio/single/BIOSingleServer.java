package com.chenyuhe.cn.bio.single;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOSingleServer {


	// 服务端的网络模型封装镀锡
	private ServerSocket server;


	public BIOSingleServer(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("单线程BIO已经启动了,监听的端口是:" + port);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 开始监听并处理业务逻辑
	 */
	public void listener() {

		Socket client = null;
		try {

			client = server.accept();
			// 获取inputStream
			InputStream in = client.getInputStream();
			// 通过字节数组去读取数据
			byte [] buffer = new byte[1024];
			int len = in.read(buffer);
			if(len>0) {
				String msg = new String(buffer,0,len);
				System.out.println("收到的信息是:" + msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {

		new BIOSingleServer(8080).listener();
	}



}