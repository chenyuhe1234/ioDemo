package com.chenyuhe.cn.bio.multipart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {

	private Socket socket;

	public ServerHandler(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {

		BufferedReader in = null;
		PrintWriter out = null;
		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			String expression;
			String result;
			while (true) {
				// 通过bufferedReader读取一行 如果已经读到输入流的尾部 返回null 退出循环 如果得到非空值 就尝试计算结果并返回
				if(((expression = in.readLine())==null || "".equals(expression))) {
					continue;
				}
				System.out.println(socket.getRemoteSocketAddress() + "消息" + expression);
				result ="BIO服务端收到消息\n";
				out.println(result);
				if("exit".equals(expression)){
					System.out.println("客户端"+socket.getRemoteSocketAddress()+" 退出了。。。");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
}