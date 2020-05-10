package com.chenyuhe.cn.bio.multipart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BIOMultipartClient {

	private static int DEFAULT_PORT = 12345;

	private static String DEFAULT_SERVER_IP = "127.0.0.1";

	private static Socket socket = null;

	public static void send(Socket socket) {

		BufferedReader in = null;
		PrintWriter out = null;

		// 客户端需要发送数据
		try {

			socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_PORT);
			Scanner input = new Scanner(System.in);//客户手动输入内容
			input.useDelimiter("\n");//可以将分隔符号修改为"回车"，或者其他字符
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				System.out.println("输入发送消息：");
				String line = input.nextLine();
				out.println(line);
				System.out.println("服务端返回：" + in.readLine());
				if ("exit".equals(line)) {
					System.out.println("客户端退出了");
					break;
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				out.close();
			}

			if (socket != null) {
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
		try {
			socket = new Socket(DEFAULT_SERVER_IP,DEFAULT_PORT);
			Scanner in = new Scanner(System.in);
			BIOMultipartClient.send(socket);
			in.useDelimiter("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}