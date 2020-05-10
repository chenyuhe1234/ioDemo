package com.chenyuhe.cn.copy;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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


	/**
	 * NIO进行文件的复制 非直接缓冲区 每次进行IO操作 就会将缓冲区的内容复制到中间缓冲区中去
	 * <p>
	 * buffer既可以用来读也可以用来写 进行模式的切换用 xxxBuffer.flip()方法
	 */
	@Test
	public void noDirectBufferFile() {

		FileInputStream in = null;
		FileOutputStream out = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			in = new FileInputStream("e:/test/123.txt");
			out = new FileOutputStream("e:/test/dest.txt");
			inChannel = in.getChannel();
			outChannel = out.getChannel();

			// 缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			// 将通道中的数据存入缓冲区
			while (inChannel.read(buffer) != -1) {
				buffer.flip();
				outChannel.write(buffer);
				buffer.clear();


			}
			System.out.println("完成了文件的复制.......");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


			if (outChannel != null) {
				try {
					outChannel.close();
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


			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * NIO的transferTo实现文件的拷贝 零拷贝技术
	 */
	@Test
	public void nioTransferTo() {

		FileChannel inChannel = null;
		FileChannel outChannel = null;

		try {


			// 读取文件
			inChannel = FileChannel.open(Paths.get("e:/test/123.txt"), StandardOpenOption.READ);

			// 输出文件
			outChannel = FileChannel.open(Paths.get("e:/test/dest.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

			inChannel.transferTo(0, inChannel.size(), outChannel);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("进行文件的复制完成.......");


	}


}