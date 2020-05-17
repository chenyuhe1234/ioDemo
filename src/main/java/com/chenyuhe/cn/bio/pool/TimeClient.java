package com.chenyuhe.cn.bio.pool;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * 客户端要做什么  要与服务端建立连接
 */
public class TimeClient {


	// 定义默认的端口号
	private static int DEFAULT_PORT = 333;

	// 定义主机ip
	private static String DEFAULT_SERVER_IP = "127.0.0.1";

	// 退出标识
	private static final String EXIT = "exit";

	private static Socket socket = null;

	public static void send(Socket socket) {

		// 输出流  输出流  我们这里用字符的会比较方便
		BufferedReader in = null;
		PrintWriter out = null;
		try {

			// 建立通信
			if(socket == null) {
				socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_PORT);
			}
			// 输出流 输入流的初始化
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			// 数据来主要哪里
			Scanner scanner = new Scanner(System.in);
			scanner.useDelimiter("\n");
			while (true) {
				System.out.println("请输入要发送的消息:");
				String line = scanner.nextLine();
				if (StringUtils.isNotBlank(line)) {
					// 如果消息不是null 将这个消息发送给服务端
					out.println(line);
					// 读取服务端的返回
					System.out.println("服务端返回是:" + in.readLine());
					if (EXIT.equals(line)) {
						System.out.println("客户端退出了 结束了会话!");
						break;
					}
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(out!=null) {
				out.close();
			}

			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}

	public static void main(String[] args) {
		Socket socket = null;
		TimeClient.send(socket);
	}


}