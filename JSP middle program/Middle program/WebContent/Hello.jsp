<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import= "java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
   <%
   File file = new File("C:\\Users\\user\\Desktop\\For.txt");
   FileInputStream fis = new FileInputStream(file);
   out.println("Total file size to read (in bytes) : "+ fis.available()+"<br/>");
   int content;
	while ((content = fis.read()) != -1) {
		// convert to char and display it
		out.print((char) content);
	}
   fis.close();
   %>
    
</body>
</html>