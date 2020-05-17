package com.chenyuhe.cn.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedBuffer {


	private static final int start = 0;
	private static final int size = 26;

	public static void main(String[] args) throws Exception {

		RandomAccessFile raf = new RandomAccessFile("E:\\test\\123.txt", "rw");
		FileChannel fc = raf.getChannel();

		// 把缓存区的内容 和 磁盘上文件的内容做了一个映射
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);
		mbb.put(0, (byte) 97); //a
		mbb.put(25, (byte) 122); // z
		raf.close();
		System.out.println("改变文件完成!");

	}
}