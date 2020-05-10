package com.chenyuhe.cn.copy;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileTest {

	/**
	 * BIO传统的文件复制 传递的通过BIO进行文件的复制底层的原理是什么
	 */
	@Test
	public void bioCopyFile() {

		File source = null;
		File dest = null;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			source = new File("e:/test/123.txt");
			dest = new File("e:/test/dest.txt");
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			// 缓存的字节数组
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {

				out.write(buffer);
				out.flush();
			}

			System.out.println("服务文件成功..... ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}