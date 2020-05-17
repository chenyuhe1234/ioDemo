package com.chenyuhe.cn.tomcat.http;

import java.io.OutputStream;

public class GPResponse {


	private OutputStream out;

	public GPResponse(OutputStream out) {
		this.out = out;
	}

	public void write(String s) throws Exception {

		// 用的是http协议 输出也要遵循http协议
		StringBuilder sb = new StringBuilder();
		sb.append("HTTP/1.1 200 OK\n")
				.append("Content-Type: text/html;\n")
				.append("\r\n")
				.append(s);
		out.write(sb.toString().getBytes());
	}
}