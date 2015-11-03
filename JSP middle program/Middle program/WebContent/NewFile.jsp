<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import = "my.MCrypt,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

       <%
        String key = "123456789";
		File file = new File("C:\\Users\\user\\Desktop\\Url.jar");//要加密的jar路徑
		FileInputStream in = new FileInputStream(file);
		long length = file.length();

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = in.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		in.close();
		MCrypt mcrypt = new MCrypt();
		byte[] en = mcrypt.getencrypt(key, bytes);
		FileOutputStream out1 = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\Url2.jar"));//加密完輸出的路徑與jar檔名
		out1.write(en);
        out1.close();
        //FileOutputStream out2 = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\Url.jar"));//解密完輸出的路徑與jar檔名
		//byte[] de = mcrypt.getdecrypt(key, en);
		//out2.write(de);
		//out2.close();
		out.print("OK");
       %>
</body>
</html>