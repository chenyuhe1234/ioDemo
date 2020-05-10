package com.chenyuhe.cn.bio.multipart;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOMultipartServer {

	// 默认端口号
	private static int DEFAULT_PORT = 12345;

	// 单例的ServerSocket
	private static ServerSocket server;


	public synchronized static void start(int port) throws IOException {
		if (server != null) {
			return;
		}
		try {
			server = new ServerSocket(port);
			System.out.println("服务器已经启动,端口号是:" + port);
			// 进行业务逻辑的处理
			while (true) {
				Socket socket = server.accept();
				System.out.println("客户端" + socket.getRemoteSocketAddress() + "连接上了.....");
				// 当有客户端过来 就开启一个新的线程去处理
				new Thread(new ServerHandler(socket)).start();
			}
		} finally {
			if (server != null) {
				System.out.println("服务器已经关闭了!");
				server.close();
				server = null;
			}
		}
	}

	// 根据传入的参数 来决定监听的端口号
	public static void start() throws IOException {

		start(DEFAULT_PORT);
	}

	public static void main(String[] args) {

		try {
			BIOMultipartServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}