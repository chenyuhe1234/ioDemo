package com.chenyuhe.cn.tomcat.http;

import java.io.InputStream;

public class GPRequest {

	private String method;
	private String url;

	public GPRequest(InputStream in) {

//		GET /secondServlet.do HTTP/1.1
//		Host: localhost:8080
//		Connection: keep-alive
//		Pragma: no-cache
//		Cache-Control: no-cache
//		Upgrade-Insecure-Requests: 1
//		User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36
//		Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
//Accept-Encoding: gzip, deflate, br
//Accept-Language: en,zh-CN;q=0.9,zh;q=0.8,zh-TW;q=0.7

		try {

			// 拿到HTTP协议的内容  对请求的内容进行处理 ------ > 主要是拿到请求的URL 和 METHOD
			String content = "";
			byte[] buff = new byte[1024];
			int len = 0;
			if ((len = in.read(buff)) > 0) {
				content = new String(buff, 0, len);
			}
			System.out.println("------------------ 请求报文 -------------------");
			System.out.println(content);
			String line = content.split("\\n")[0];
			String[] arr = line.split("\\s");
			this.method = arr[0];
			this.url = arr[1].split("\\?")[0];

			System.out.println("method方法是:" + this.method);
			System.out.println("url的值是:" + this.url);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}