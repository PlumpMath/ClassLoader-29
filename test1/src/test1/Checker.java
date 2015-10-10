package test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLClassLoader;

import test1.InterfaceAction;
import test1.MCrypt;

public class Checker {

	public static void main(String[] args) throws Exception {

		aes();
		checker();

	}
	
	//loadClass 對解密完的jar載入執行
	private static void checker() throws Exception {
		// TODO Auto-generated method stub
		try {
			URL url1 = new URL("file:C:/Users/Lai/Desktop/c.jar"); //解密完的jar路徑
			URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 },
					Thread.currentThread().getContextClassLoader());
			Class<?> myClass1 = myClassLoader1.loadClass("test1.TestAction");
			InterfaceAction action1 = (InterfaceAction) myClass1.newInstance();
			String str1 = action1.action();
			System.out.println(str1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 載入jar
	private static void aes() throws Exception {
		// TODO Auto-generated method stub
		String key = "123456789";
		File file = new File("C:\\Users\\Lai\\Desktop\\a.jar");//要加密的jar路徑
		FileInputStream in = new FileInputStream(file);
		long length = file.length();

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = in.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		test(key, bytes);
	}

	// 進行加解密
	private static void test(String key, byte[] bytes) throws Exception {
		// TODO Auto-generated method stub
		MCrypt mcrypt = new MCrypt();
		// 加密
		byte[] en = mcrypt.getencrypt(key, bytes);
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Lai\\Desktop\\b.jar"));//加密完輸出的路徑與jar檔名
		// byte[] de=getdecrypt(key, en);
		out.write(en);

		// 解密
		FileOutputStream out1 = new FileOutputStream(new File("C:\\Users\\Lai\\Desktop\\c.jar"));//解密完輸出的路徑與jar檔名
		byte[] de = mcrypt.getdecrypt(key, en);
		out1.write(de);
	}
}
