package com.chenyuhe.cn.nio;

import org.junit.Test;
import sun.swing.BakedArrayList;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class BufferDemo {

	/**
	 * 缓冲区状态实时应答
	 */
	public static void outputStr(String step, ByteBuffer buffer) {

		System.out.println(step + ":");
		// 容器 也就是数组的大小
		System.out.print("capacity:" + buffer.capacity() + ",");
		//当前操作数据所在的位置，也可以叫做游标
		System.out.println("position:" + buffer.position() + ",");
		// flip 反转
		System.out.println("limit:" + buffer.limit());
		System.out.println();

	}

	@Test
	public void test1() {

		// 定义缓冲区的方式2
		byte[] arr = new byte[10];
		ByteBuffer buffer1 = ByteBuffer.wrap(arr);
		System.out.println(buffer1);

	}


	public static void main(String[] args) {

		// 需要获取Channel
		try {

			FileInputStream in = new FileInputStream("E:\\test\\123.txt");
			FileChannel fileChannel = in.getChannel();

			// 缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(10);
			outputStr("初始化", buffer);


			// 定义缓冲区的方式2
//			byte[] arr = new byte[10];
//			ByteBuffer buffer1 = ByteBuffer.wrap(arr);

			// 先将磁盘中的内容 读取到缓冲区中去
			fileChannel.read(buffer);
			outputStr("调用了read()方法", buffer);

			buffer.flip();
			outputStr("调用了flip()方法 表示要对缓冲区做get操作", buffer);
			List<Character> charList = new ArrayList<>();
			byte[] resultByte = new byte[buffer.capacity()];
			// 要从缓冲区读取数据
			while (buffer.remaining() > 0) {
				byte b = buffer.get();
				System.out.print(((char) b));
				charList.add((char) b);
			}
			System.out.println(charList.toString());

			System.out.println("----------------------------------");
			buffer.clear();
			outputStr("调用clear()方法", buffer);

			// 关闭管道

			fileChannel.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}