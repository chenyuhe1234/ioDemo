package com.chenyuhe.cn.bio.pool;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在某个端口提供服务
 */
public class TimeServer {

	private static final int port = 333;
	private static ServerSocket serverSocket = null;

	private static ExecutorService executor = Executors.newFixedThreadPool(100);

	// 引入线程池  固定的线程数:对应多个客户端

	public static void receive(ServerSocket serverSocket) {

		try {
			Socket socket = null;
			if(serverSocket == null) {
				serverSocket = new ServerSocket(port);
			}
			System.out.println("服务器已经启动,端口号是:" + port);
			socket = serverSocket.accept();
			// 使用线程池 将socket包装成task进行任务的处理
			executor.submit(new TimeHandler(socket));




		} catch (Exception e) {
			e.printStackTrace();
		}




	}


	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		TimeServer.receive(serverSocket);
	}







}