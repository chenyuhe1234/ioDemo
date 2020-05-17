package com.chenyuhe.cn.bio.pool;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;

public class TimeHandler implements Runnable {


	private Socket socket;

	// 退出标识
	private static final String EXIT = "exit";

	public TimeHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		BufferedReader in = null;
		PrintWriter out = null;
		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			while (true) {
				// 读取客户端发送过来的消息
				String line = in.readLine();
				System.out.println("客户端:" + socket.getRemoteSocketAddress() + "消息是:" + line);
				if (StringUtils.isBlank(line)) {
					out.println("客户端发送过来的消息是null");
					break;
				}

				if (EXIT.equals(line)) {
					System.out.println("客户端:" + socket.getRemoteSocketAddress() + "会话结束!");
					break;
				}

				// 正常的回应客户端
				out.println(line);
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
}