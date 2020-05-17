package com.chenyuhe.cn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 我们从磁盘读取文件 首先要经过【机器内存】 在到 【JVM内存】
 * 而直接缓冲区 省略了【JVM内存】 直接操作【机器内存】 java存放的只是缓冲区的引用地址
 */
public class DirectBuffer {


	public static void main(String[] args) throws Exception {
		// 在java里存放是缓冲区的引用地址

		String inFile = "E:\\test\\123.txt";
		FileInputStream in = new FileInputStream(inFile);
		FileChannel inChannel = in.getChannel();


		// 把刚刚读取的文件内容写入到一个新文件中
		String outFile = String.format("E:\\test\\555.txt");
		FileOutputStream out = new FileOutputStream(outFile);
		FileChannel outChannel = out.getChannel();

		// 直接缓冲区
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		while (true) {

			buffer.clear();

			int r = inChannel.read(buffer);
			if (r == -1) {
				break;

			}

			// 转换模式
			buffer.flip();
			outChannel.write(buffer);
		}
		System.out.println("------- 使用直接缓冲区进行文件的复制完成----------");


	}

}