package com.chenyuhe.cn.tomcat;

import com.chenyuhe.cn.tomcat.http.GPRequest;
import com.chenyuhe.cn.tomcat.http.GPResponse;
import com.chenyuhe.cn.tomcat.http.GPServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {


	// J2EE标准

	// Servlet

	// Request


	// Response

	// 1.配置好启动端口 默认是8080 ServerSocket ip:localhost
	// 2.配置web.xml 自己写Servlet继承HttpServlet
	// servlet-name
	// servlet-class
	// url-pattern
	// 3.读取配置 url-pattern 和 Servlet建立一个映射关系
	// 4.http请求 发送的数据就是字符串 有规律的字符串(HTTP协议)
	// 5.从协议内容中拿到URL 把相应的Servlet用反射进行实例化
	// 6.调用实例化对象的service()方法 执行具体的逻辑doGet/doPost方法
	// 7.Request(InputStream) / Response(OutputSteam)


	private int port = 8080;
	private ServerSocket server;
	private Map<String, GPServlet> servletMapping = new HashMap<String, GPServlet>();
	private Properties webxml = new Properties();


	/**
	 * 初始化方法
	 */
	private void init() {

		// 加载web.xml文件 同时初始化 ServletMapping对象
		try {
			String WEB_INF = this.getClass().getResource("/").getPath();
			FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
			webxml.load(fis); // 将inputStream ----- > properties对象
			for (Object k : webxml.keySet()) {

				String key = k.toString();
				if (key.endsWith(".url")) {
					String servletName = key.replaceAll("\\.url$", "");
					String url = webxml.getProperty(key);
					String className = webxml.getProperty(servletName + ".className");

					// 将servlet进行了初始化 放到了map容器中 servlet是单实例的 多线程的
					GPServlet obj = (GPServlet) Class.forName(className).newInstance();
					servletMapping.put(url, obj);

				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 服务启动方法
	 */
	public void start() {

		// 1.加载配置文件 初始化ServletMapping
		init();

		// 启动容器 在指定的端口提供服务
		try {

			server = new ServerSocket(this.port);
			System.out.println("GP Tomcat已经启动  监听的端口是:" + this.port);


			// 用一个死循环 等待用户请求
			while (true) {

				Socket client = server.accept();
				process(client);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 处理请求
	 * @param client
	 * @throws Exception
	 */
	private void process(Socket client) throws Exception {


		InputStream is = client.getInputStream();
		OutputStream os = client.getOutputStream();

		GPRequest request = new GPRequest(is);
		GPResponse response = new GPResponse(os);

		String url = request.getUrl();

		if (servletMapping.containsKey(url)) {
			servletMapping.get(url).service(request, response);
		} else {
			response.write("404-not found ......");
		}
		os.flush();
		os.close();

		client.close();
		is.close();
	}
	
	
	public static void main(String[] args) {

		new GPTomcat().start();
	}


}