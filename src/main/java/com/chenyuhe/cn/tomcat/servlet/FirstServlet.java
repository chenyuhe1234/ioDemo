package com.chenyuhe.cn.tomcat.servlet;

import com.chenyuhe.cn.tomcat.http.GPRequest;
import com.chenyuhe.cn.tomcat.http.GPResponse;
import com.chenyuhe.cn.tomcat.http.GPServlet;

public class FirstServlet extends GPServlet {
	@Override
	public void doGet(GPRequest request, GPResponse response) throws Exception {

		this.doPost(request,response);
	}

	@Override
	public void doPost(GPRequest request, GPResponse response) throws Exception {
		response.write("this is first servlet.....");
	}
}